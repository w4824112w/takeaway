package com.takeaway.modular.service;

import java.io.File;
import java.util.ArrayList;
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
import com.takeaway.modular.dao.dto.ItemPicturesDto;
import com.takeaway.modular.dao.dto.ItemsDto;
import com.takeaway.modular.dao.mapper.ItemMerchantsMapper;
import com.takeaway.modular.dao.mapper.ItemPicturesMapper;
import com.takeaway.modular.dao.mapper.ItemPropertysMapper;
import com.takeaway.modular.dao.mapper.ItemTypesMapper;
import com.takeaway.modular.dao.mapper.ItemsMapper;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.mapper.PropertysMapper;
import com.takeaway.modular.dao.model.ItemMerchants;
import com.takeaway.modular.dao.model.ItemPictures;
import com.takeaway.modular.dao.model.ItemPropertys;
import com.takeaway.modular.dao.model.ItemTypes;
import com.takeaway.modular.dao.model.Items;
import com.takeaway.modular.dao.model.Propertys;

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
	private ItemTypesMapper itemTypesMapper;

	@Autowired
	private ItemPicturesMapper itemPicturesMapper;

	@Autowired
	private ItemMerchantsMapper itemMerchantsMapper;

	@Autowired
	private ItemPropertysMapper itemPropertysMapper;

	@Autowired
	private PropertysMapper propertysMapper;

	@Autowired
	private MerchantsMapper merchantsMapper;

	public PageResult<ItemsDto> findPage(PageBounds bounds, ItemsDto dto) {
		PageList<ItemsDto> items = itemsMapper.findPage(bounds, dto);
		for (ItemsDto item : items) {
			List<ItemPicturesDto> pictures = itemPicturesMapper.getByItemId(item
					.getId().toString());
			item.setPictures(pictures);
		}
		return new PageResult<ItemsDto>(items);
	}

	@Transactional
	public JSONObject save(Items items) {
		int result;
		items.setStatus(1);
		items.setCreatedAt(new Date());
		result = itemsMapper.save(items);
		Integer itemId = items.getId();

		for (ItemMerchants itemMerchants : items.getItemMerchants()) {
			itemMerchants.setItemId(itemId);
			itemMerchantsMapper.save(itemMerchants);
		}

		for (ItemPropertys itemPropertys : items.getItemPropertys()) {
			itemPropertys.setItemId(itemId);
			itemPropertysMapper.save(itemPropertys);
		}

		for (ItemPictures itemPictures : items.getPictures()) {
			itemPictures.setItemId(itemId);
			itemPictures.setCreatedAt(new Date());
			itemPicturesMapper.save(itemPictures);
		}

		ItemTypes itemTypes = new ItemTypes();
		itemTypes.setId(items.getItemType());
		itemTypesMapper.increase(itemTypes);

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

		List<ItemPicturesDto> oldPictures = itemPicturesMapper.getByItemId(itemId
				.toString());

		result = itemsMapper.update(items);
		itemMerchantsMapper.delByItemId(itemId.toString());
		itemPropertysMapper.delByItemId(itemId.toString());
		itemPicturesMapper.delByItemId(itemId.toString());

		for (ItemMerchants itemMerchants : items.getItemMerchants()) {
			itemMerchants.setItemId(itemId);
			itemMerchantsMapper.save(itemMerchants);
		}

		for (ItemPropertys itemPropertys : items.getItemPropertys()) {
			itemPropertys.setItemId(itemId);
			itemPropertysMapper.save(itemPropertys);
		}

		for (ItemPictures merchantPictures : items.getPictures()) {
			merchantPictures.setItemId(itemId);
			merchantPictures.setCreatedAt(new Date());
			itemPicturesMapper.save(merchantPictures);
		}

		// 更新背景图片
		for (ItemPicturesDto picture : oldPictures) {
			// String upload_dir = UploadController.resourcePath();// 上传路径
			// File file = new File(upload_dir + picture.getUrl());
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
	public JSONObject superUpdate(ItemMerchantsDto itemMerchants) {
		int result;
		result = itemMerchantsMapper.superUpdate(itemMerchants);
		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改商品", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改商品", result);
		}
	}

	public ItemsDto getById(String id) {
		ItemsDto items = itemsMapper.getById(id);
		items.setMerchants(merchantsMapper.getByItemId(id));

		List<Propertys> pids = propertysMapper.getPidByItemId(id);
		List<Propertys> retPropertys = new ArrayList<Propertys>();
		for (Propertys pid : pids) {
			Propertys ret = propertysMapper.getById(pid.getPid().toString());
			Propertys dto = new Propertys();
			dto.setPid(ret.getId());
			dto.setItemId(id);
			ret.setSubPropertys(propertysMapper.getByItemIdAndPid(dto));
			;
			retPropertys.add(ret);
		}
		items.setPropertys(retPropertys);
		items.setPictures(itemPicturesMapper.getByItemId(id));
		return items;
	}

	@Transactional
	public int delete(String id) {
		List<ItemPicturesDto> itemPictures = itemPicturesMapper.getByItemId(id);
		for (ItemPicturesDto picture : itemPictures) {
			String upload_dir = UploadController.resourcePath();// 上传路径
			File file = new File(upload_dir + picture.getUrl());
			file.delete();
		}

		ItemsDto items = itemsMapper.getById(id);
		ItemTypes itemTypes = new ItemTypes();
		itemTypes.setId(Integer.parseInt(items.getItemType()));
		itemTypesMapper.reduce(itemTypes);

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
