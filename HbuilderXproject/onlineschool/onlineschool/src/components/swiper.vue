<template>
	<div class="swiper">
		<div class="swiper-container"></div>
		<el-carousel  :interval="4000" type="card">
			<el-carousel-item class="carpic" v-for="(item, index) in swiperList" :key="index">
				<img  :src="item.spic" style="width: 100%;width: 100%;"/>
			</el-carousel-item>
		</el-carousel>
	</div>
</template>

<script>
	export default {
		name: 'swiper',
		props: {
			swiperList: Array,
		},
		
	}
</script>
<style>
	.swiper {
		width: 90%;
		margin: auto;
		margin-top: 30px;

		img {
			width: 100%;
		}
	}
</style>
