import request from '@/utils/request'

// 查询短信日志列表
export function listSmsLog(query) {
  return request({
    url: '/sms/log/list',
    method: 'get',
    params: query
  })
}

// 查询短信发送记录详细
export function getLog(id) {
  return request({
    url: '/sms/log/' + id,
    method: 'get'
  })
} 