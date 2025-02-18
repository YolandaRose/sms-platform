import request from '@/utils/request'

// 查询短信用户列表
export function listSmsuser(query) {
  return request({
    url: '/sms/smsuser/list',
    method: 'get',
    params: query
  })
}

// 查询短信用户详细
export function getSmsuser(id) {
  return request({
    url: '/sms/smsuser/' + id,
    method: 'get'
  })
}

// 新增短信用户
export function addSmsuser(data) {
  return request({
    url: '/sms/smsuser',
    method: 'post',
    data: data
  })
}

// 修改短信用户
export function updateSmsuser(data) {
  return request({
    url: '/sms/smsuser',
    method: 'put',
    data: data
  })
}

// 删除短信用户
export function delSmsuser(id) {
  return request({
    url: '/sms/smsuser/' + id,
    method: 'delete'
  })
}
