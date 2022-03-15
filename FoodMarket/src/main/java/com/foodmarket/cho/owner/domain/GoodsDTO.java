package com.foodmarket.cho.owner.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoodsDTO {

	private int goods_uid;
	private String goods_category;
	private String goods_kind;
	private String goods_name;
	private int goods_price;
	private String goods_explain;
	private String goods_filePath;
	private int goods_quantity;
}
