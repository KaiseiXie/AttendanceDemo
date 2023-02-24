//获取打卡时间
function clockTimeListApi() {
    return $axios({
      'url': '/attendance/list',
      'method': 'get',
    })
  }

//获取最新地址
function addressLastUpdateApi() {
    return $axios({
      'url': '/addressBook/lastUpdate',
      'method': 'get',
    })
}

//打卡上班
function  addClockTimeApi(){
    return $axios({
        'url': '/employee/clockin',
        'method': 'post',
      })
}

//打卡下班
function  updateClockTimeApi(){
    return $axios({
        'url': '/employee/clockout',
        'method': 'post',
    })
}



//删除地址
function deleteAddressApi(params) {
    return $axios({
        'url': '/addressBook',
        'method': 'delete',
        params
    })
}

//查询单个地址
function addressFindOneApi(id) {
  return $axios({
    'url': `/addressBook/${id}`,
    'method': 'get',
  })
}

//设置默认地址
function  setDefaultAddressApi(data){
  return $axios({
      'url': '/addressBook/default',
      'method': 'put',
      data
    })
}

//获取默认地址
function getDefaultAddressApi() {
  return $axios({
    'url': `/addressBook/default`,
    'method': 'get',
  })
}