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
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.modular.controller.web.UploadController;
import com.takeaway.modular.dao.dto.MerchantsDto;
import com.takeaway.modular.dao.dto.UserFavoritesDto;
import com.takeaway.modular.dao.mapper.ActivitysMapper;
import com.takeaway.modular.dao.mapper.CouponsMapper;
import com.takeaway.modular.dao.mapper.ManagersMapper;
import com.takeaway.modular.dao.mapper.MerchantPicturesMapper;
import com.takeaway.modular.dao.mapper.MerchantsMapper;
import com.takeaway.modular.dao.mapper.OrdersMapper;
import com.takeaway.modular.dao.mapper.UserFavoritesMapper;
import com.takeaway.modular.dao.model.Coupons;
import com.takeaway.modular.dao.model.ItemPictures;
import com.takeaway.modular.dao.model.Managers;
import com.takeaway.modular.dao.model.MerchantPictures;
import com.takeaway.modular.dao.model.Merchants;

/**
 * 本地的
 * 
 * @author Administrator
 *
 */
@Service
public class MerchantsService {
	@Autowired
	private MerchantsMapper merchantsMapper;

	@Autowired
	private ManagersMapper managersMapper;

	@Autowired
	private OrdersMapper ordersMapper;

	@Autowired
	private CouponsMapper couponsMapper;
	
	@Autowired
	private ActivitysMapper activitysMapper;
	
	@Autowired
	private UserFavoritesMapper userFavoritesMapper;

	@Autowired
	private MerchantPicturesMapper merchantPicturesMapper;

	public PageResult<MerchantsDto> findPage(PageBounds bounds, MerchantsDto dto) {
		PageList<MerchantsDto> merchants = merchantsMapper
				.findPage(bounds, dto);
		return new PageResult<MerchantsDto>(merchants);
	}

	@Transactional
	public JSONObject save(Merchants merchants) {
		int count = managersMapper.checkLoginName(merchants.getAccountName());
		if (count > 0) {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "账户名称已经存在", null);
		}

		int result;
		merchants.setStatus(1);
		merchants.setCreatedAt(new Date());
		result = merchantsMapper.save(merchants);
		Integer merchantId = merchants.getId();
		
		for (MerchantPictures merchantPictures : merchants.getPictures()) {
			merchantPictures.setMerchantId(merchantId);
			merchantPictures.setCreatedAt(new Date());
			merchantPicturesMapper.save(merchantPictures);
		}

		// 默认密码，md5加密
		Managers user=new Managers();
		user.setPasswordHash(MD5Util.MD5(merchants.getAccountPassword()));
		user.setRoleId(1);
		user.setType(0); // (0：普通;1：超级;)
		user.setStatus(1);
		user.setCreatedAt(new Date());
		managersMapper.save(user);

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "新增商户", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "新增商户", result);
		}
	}

	@Transactional
	public JSONObject update(Merchants merchants) {
		int result;
		merchants.setStatus(1);
		merchants.setUpdatedAt(new Date());
		Integer merchantId = merchants.getId();

		List<MerchantPictures> oldPictures = merchantPicturesMapper
				.getByMerchantId(merchantId.toString());

		result = merchantsMapper.update(merchants);
		
		merchantPicturesMapper.delByMerchantId(merchantId.toString());

		for (MerchantPictures merchantPictures : merchants.getPictures()) {
			merchantPictures.setMerchantId(merchantId);
			merchantPictures.setCreatedAt(new Date());
			merchantPicturesMapper.save(merchantPictures);
		}

		// 更新背景图片
		if (oldPictures != null) {
			for (MerchantPictures picture : oldPictures) {
				String upload_dir = UploadController.resourcePath();// 上传路径
				File file = new File(upload_dir + picture.getUrl());
				file.delete();
			}
		}

		if (result > 0) {
			return ErrorEnums.getResult(ErrorEnums.SUCCESS, "修改商户", result);
		} else {
			return ErrorEnums.getResult(ErrorEnums.ERROR, "修改商户", result);
		}
	}

	public Merchants getById(String id) {
		Merchants merchants = merchantsMapper.getById(id);
		merchants.setPictures(merchantPicturesMapper.getByMerchantId(id));
		return merchants;
	}

	@Transactional
	public int delete(String id) {
		List<MerchantPictures> merchantPictures = merchantPicturesMapper
				.getByMerchantId(id);
		for (MerchantPictures picture : merchantPictures) {
			String upload_dir = UploadController.resourcePath();// 上传路径
			File file = new File(upload_dir + picture.getUrl());
			file.delete();
		}

		merchantPicturesMapper.delByMerchantId(id);
		return merchantsMapper.delete(id);

	}

	public List<Merchants> getAll() {
		return merchantsMapper.getAll();
	}
	
	public List<Merchants> getByItemId(String itemId) {
		return merchantsMapper.getByItemId(itemId);
	}

	public List<MerchantsDto> getAllByUserId(String userId) {
		List<MerchantsDto> merchants = merchantsMapper.appIndex();
		for (MerchantsDto dto : merchants) {
			String merchantId = dto.getId().toString();
			dto.setMonthOrder(ordersMapper
					.getMonthOrdersByMerchantId(merchantId) + "");
			UserFavoritesDto query = new UserFavoritesDto();
			query.setTargetId(merchantId);
			query.setUserId(userId);
			if (userFavoritesMapper.getfavorites(query).size() > 0) {
				dto.setIsFavorite(true);
			} else {
				dto.setIsFavorite(false);
			}
			dto.setCoupons(couponsMapper.getByMerchantId(merchantId));
			dto.setActivitys(activitysMapper.getByMerchantId(merchantId));
		}
		return merchants;
	}

}
