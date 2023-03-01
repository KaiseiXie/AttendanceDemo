// 查询列表接口
const getAttendancePage = (params) => {
  return $axios({
    url: '/attendance/page',
    method: 'get',
    params
  })
}

// 编辑页面反查详情接口
const queryCategoryById = (id) => {
  return $axios({
    url: `/category/${id}`,
    method: 'get'
  })
}

// 删除当前列的接口
const deleAttendance = (timesIn) => {
  return $axios({
    url: '/attendance',
    method: 'delete',
    params: { timesIn }
  })
}

// 修改接口
const editAttendance = (params) => {
  return $axios({
    url: '/attendance',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addCategory = (params) => {
  return $axios({
    url: '/category',
    method: 'post',
    data: { ...params }
  })
}