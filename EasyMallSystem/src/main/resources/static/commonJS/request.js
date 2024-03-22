import axios from "../js/axios.js";

const instance = axios.create({
    baseURL: '/',
    // timeout: 5000
})

// 添加请求拦截器
instance.interceptors.request.use(async (request) => {
    // 添加签名和时间戳
  let resp=await axios.get('/getUUID')
    console.log(resp.data)
    request.headers['signature'] = resp.data;
    request.headers['timestamp'] = Date.now();
    return request;
}, (error) => {
    // 处理错误
    return Promise.reject(error);
});

// 添加响应拦截器
instance.interceptors.response.use(request => request.data, error => Promise.reject(error))

export default instance
