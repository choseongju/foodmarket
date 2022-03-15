package com.foodmarket.cho.member.basket.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BasketDTO {

	private String basket_uid;
	private String goods_uid;
	private String goods_name;
	private String goods_select_quantity;
	private String goods_price;
	private String goods_filePath;
	private String mb_id;
	private String order_cnt;
}
