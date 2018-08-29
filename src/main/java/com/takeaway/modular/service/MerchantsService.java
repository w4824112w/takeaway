package com.takeaway.modular.service;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.takeaway.commons.page.PageBounds;
import com.takeaway.commons.page.PageList;
import com.takeaway.commons.page.PageResult;
import com.takeaway.commons.utils.MD5Util;
import com.takeaway.commons.utils.MapDistance;
import com.takeaway.core.enums.ErrorEnums;
import com.takeaway.core.netpay.wxpay.utils.Configure;
import com.takeaway.core.netpay.wxpay.utils.HttpsRequest;
import com.takeaway.modular.controller.web.UploadController;
import com.takeaway.modular.dao.dto.FeedbacksDto;
import com.takeaway.modular.dao.dto.MerchantsDto;
import com.takeaway.modular.dao.dto.UserFavoritesDto;
import com.takeaway.modular.dao.mapper.ActivitysMapper;
import com.takeaway.modular.dao.mapper.CouponsMapper;
import com.takeaway.modular.dao.mapper.FeedbacksMapper;
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
	
	@Autowired
	private FeedbacksMapper feedbacksMapper;
	
	@Autowired
	private RestTemplate restTemplate;

	public PageResult<MerchantsDto> findPage(PageBounds bounds, MerchantsDto dto) {
		PageList<MerchantsDto> merchants = merchantsMapper
				.findPage(bounds, dto);
		for(MerchantsDto merchant:merchants){
			FeedbacksDto query=new FeedbacksDto();
			query.setMerchantId(merchant.getId());
			String score=feedbacksMapper.getTotalScoreByMerchantId(query);
			merchant.setScore(score);
		}
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
		Managers user = new Managers();
		user.setName(merchants.getAccountName());
		user.setPasswordHash(MD5Util.MD5(merchants.getAccountPassword()));
		user.setRoleId(1);
		user.setType(0); // (0：普通;1：超级;)
		user.setStatus(1);
		user.setMerchantId(merchantId);
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
	
	@Transactional
	public JSONObject updateDeliveryTime(String deliveryTime) {
		int result = 0;
		
		List<Merchants> merchants=merchantsMapper.getAll();
		for(Merchants merchant:merchants){
			merchant.setDeliveryTime(deliveryTime);
			merchantsMapper.update(merchant);
			result++;
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

	public MerchantsDto homePage(String id, Integer type) {
		MerchantsDto dto = new MerchantsDto();
		if (type != 1) {
			dto.setId(id);
		}
		return merchantsMapper.homePage(dto);
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

	public List<MerchantsDto> getAllByUserId(String lat,String lng,String userId,String name){
		MerchantsDto merchants_query=new MerchantsDto();
		merchants_query.setName(name);
		List<MerchantsDto> merchants = merchantsMapper.appIndex(merchants_query);
		for (MerchantsDto dto : merchants) {
			if(StringUtils.isNotBlank(lat)&&StringUtils.isNotBlank(lng)&&StringUtils.isNotBlank(dto.getLat())&&StringUtils.isNotBlank(dto.getLng())){
				dto.setDistance(MapDistance.getDistance(lng,lat,dto.getLng(),dto.getLat()));
				
			    //HTTP请求器
				String result="";
				try {
					HttpsRequest httpsRequest = new HttpsRequest("noCert");
					String origins=lng+","+lat;
					String destinations=dto.getLng()+","+dto.getLat();
					result = httpsRequest.sendGet("http://api.map.baidu.com/routematrix/v2/driving",origins,destinations);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if(StringUtils.isNotBlank(result)){
					JSONObject obj=(JSONObject) JSONObject.parse(result);
					JSONArray ret_array=(JSONArray) obj.get("result");
					JSONObject ret_obj=(JSONObject) ret_array.get(0);
					Map duration=(Map) ret_obj.get("duration");
					int time=Integer.parseInt(duration.get("value").toString())/60;
					dto.setDuration(time+"");
				}
			}
			
			String merchantId = dto.getId().toString();
			List<MerchantPictures> pictures = merchantPicturesMapper
					.getByMerchantId(merchantId.toString());
			dto.setPictures(pictures);
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
			
			FeedbacksDto query_score=new FeedbacksDto();
			query_score.setMerchantId(dto.getId());
			String score=feedbacksMapper.getTotalScoreByMerchantId(query_score);
			dto.setScore(score);
		}
		
		 Collections.sort(merchants,new Comparator () {
	            @Override
	            public int compare(Object o1, Object o2) {
	                if(o1 instanceof MerchantsDto && o2 instanceof MerchantsDto){
	                	MerchantsDto m1 = (MerchantsDto) o1;
	                	MerchantsDto m2 = (MerchantsDto) o2;
	                    return Integer.parseInt(m1.getDistance()) - Integer.parseInt(m2.getDistance());
	                }
	                throw new ClassCastException("不能转换为MerchantsDto类型");
	            }
	        });
		return merchants;
	}

	
}
