package com.takeaway.modular.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.controller.web.UploadController;
import com.takeaway.modular.dao.dto.ItemMerchantsDto;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.mapper.ItemMerchantsMapper;
import com.takeaway.modular.dao.mapper.ItemPicturesMapper;
import com.takeaway.modular.dao.mapper.ItemPropertysMapper;
import com.takeaway.modular.dao.mapper.ItemsMapper;
import com.takeaway.modular.dao.model.ItemMerchants;
import com.takeaway.modular.dao.model.ItemPictures;
import com.takeaway.modular.dao.model.ItemPropertys;
import com.takeaway.modular.dao.model.ItemTypes;
import com.takeaway.modular.dao.model.Items;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class ItemsService {
	@Autowired
	private ItemsMapper itemsMapper;

	@Autowired
	private ItemPicturesMapper itemPicturesMapper;

	@Autowired
	private ItemMerchantsMapper itemMerchantsMapper;

	@Autowired
	private ItemPropertysMapper itemPropertysMapper;

	public PageResult<Items> findPage(PageBounds bounds, ItemsDto dto) {
		PageList<Items> merchants = itemsMapper.findPage(bounds, dto);
		return new PageResult<Items>(merchants);
	}

	@Transactional
	public JSONObject save(Items items) {
		int result;
		items.setStatus(1);
		items.setCreatedAt(new Date());
		result = itemsMapper.save(items);
		Integer itemId = items.getId();

		for (ItemMerchants itemMerchants : items.getMerchants()) {
			itemMerchants.setItemId(itemId);
			itemMerchantsMapper.save(itemMerchants);
		}

		for (ItemPropertys itemPropertys : items.getPropertys()) {
			itemPropertys.setItemId(itemId);
			itemPropertysMapper.save(itemPropertys);
		}

		for (ItemPictures itemPictures : items.getPictures()) {
			itemPictures.setItemId(itemId);
			itemPictures.setCreatedAt(new Date());
			itemPicturesMapper.save(itemPictures);
		}

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增商品", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增商品", result);
		}
	}

	@Transactional
	public JSONObject update(Items items) {
		int result;
		items.setStatus(1);
		items.setUpdatedAt(new Date());
		Integer itemId = items.getId();

		List<ItemPictures> oldPictures = itemPicturesMapper.getByItemId(itemId
				.toString());

		result = itemsMapper.update(items);
		itemMerchantsMapper.delByItemId(itemId.toString());
		itemPropertysMapper.delByItemId(itemId.toString());
		itemPicturesMapper.delByItemId(itemId.toString());

		for (ItemMerchants itemMerchants : items.getMerchants()) {
			itemMerchants.setItemId(itemId);
			itemMerchantsMapper.save(itemMerchants);
		}

		for (ItemPropertys itemPropertys : items.getPropertys()) {
			itemPropertys.setItemId(itemId);
			itemPropertysMapper.save(itemPropertys);
		}

		for (ItemPictures merchantPictures : items.getPictures()) {
			merchantPictures.setCreatedAt(new Date());
			itemPicturesMapper.save(merchantPictures);
		}

		// 更新背景图片
		for (ItemPictures picture : oldPictures) {
		//	String upload_dir = UploadController.resourcePath();// 上传路径
		//	File file = new File(upload_dir + picture.getUrl());
			File file = new File(picture.getUrl());
			file.delete();
		}

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改商品", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改商品", result);
		}
	}

	@Transactional
	public JSONObject updateIsPuton(ItemMerchantsDto itemMerchants) {
		int result;
		result = itemMerchantsMapper.update(itemMerchants);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改商品", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改商品", result);
		}
	}

	@Transactional
	public JSONObject superUpdate(String itemId, String isPuton) {
		ItemMerchantsDto itemMerchants = new ItemMerchantsDto();
		itemMerchants.setItemId(itemId);
		itemMerchants.setIsPuton(isPuton);
		int result;
		result = itemMerchantsMapper.superUpdate(itemMerchants);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改商品", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改商品", result);
		}
	}

	public Items getById(String id) {
		Items items = itemsMapper.getById(id);
		items.setPictures(itemPicturesMapper.getByItemId(id));
		return items;
	}

	@Transactional
	public int delete(String id) {
		List<ItemPictures> itemPictures = itemPicturesMapper.getByItemId(id);
		for (ItemPictures picture : itemPictures) {
			String upload_dir = UploadController.resourcePath();// 上传路径
			File file = new File(upload_dir + picture.getUrl());
			file.delete();
		}

		itemPicturesMapper.delByItemId(id);
		itemMerchantsMapper.delByItemId(id.toString());
		return itemsMapper.delete(id);

	}

	public List<Items> getAll() {
		List<Items> items = itemsMapper.getAll();
		return items;
	}
	
	public List<Items> getAllByMerchantId(String merchantId) {
		return itemsMapper.getByMerchantId(merchantId);
	}

}
