var app=new Vue({
    el:'#app',
    data:{
        message:'',          //提示信息
        userId:'',        //登录用户名
        password:''         //登录密码
    },
    methods:{
        //登录方法
        login:function () {
            //提示信息
            this.message='正在登录....';
            //发送请求
            axios.post('/login?userId='+this.userId+'&password='+this.password)
                .then(resp => {
                    console.log(resp);
                    //登录响应提示
                    // this.message=resp.data.message;
                    //登录成功,跳转到后台界面
                    location.href='/index';

            }).catch(resp => {
                // this.message=resp.data.message;
            });
        }
    }
});