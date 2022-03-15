package com.foodmarket.cho.member.mainForm.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoodsCountDTO {
	private int goods_uid;
	private String goods_name;
	private int goods_viewCount;
	private int goods_likeCount;
}
