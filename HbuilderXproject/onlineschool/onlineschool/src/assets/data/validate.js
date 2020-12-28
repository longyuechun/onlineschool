export function isvalidUsername(str){
	// 用户名要求 数字、字母、下划线的组合，其中数字和字母必须同时存在*
	const reg = /^(?![^A-Za-z]+$)(?![^0-9]+$)[0-9A-Za-z_]{4,15}$/;
	return reg.test(str);
}

export function isvalidPass(str) {
  const reg = /^[a-zA-Z]\w{8,24}$/;
  return reg.test(str);
}