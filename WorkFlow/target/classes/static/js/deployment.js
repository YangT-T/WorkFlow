var app = new Vue({
    el:"#app",
    data:{
        deploymentList:[]
    },
    created:function () {
        //调用方法
        this.findAll();
    },
    methods:{
        //到后台获取列表数据
        findAll:function () {
            axios.get('/deployment/getAll')
                .then(function(result){
                    app.deploymentList = result.data;
                });
        },
        createInstance:function (id) {
            axios.get('/deployment/createInstance?id='+id)
                .then(function(result){

                });
        }
    }
});