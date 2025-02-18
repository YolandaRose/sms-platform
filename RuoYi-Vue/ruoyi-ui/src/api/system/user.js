import request from '@/utils/request'

// 查询短信用户列表
export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

// 查询短信用户详细
export function getUser(id) {
  return request({
    url: '/system/user/' + id,
    method: 'get'
  })
}

// 新增短信用户
export function addUser(data) {
  return request({
    url: '/system/user',
    method: 'post',
    data: data
  })
}

// 修改短信用户
export function updateUser(data) {
  return request({
    url: '/system/user',
    method: 'put',
    data: data
  })
}

// 删除短信用户
export function delUser(id) {
  return request({
    url: '/system/user/' + id,
    method: 'delete'
  })
}
