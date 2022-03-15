package com.foodmarket.cho.member.goodsdetails.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoodsLikeDTO {

	private String mb_id;
	private int goods_uid;
	private String goods_name;
}
