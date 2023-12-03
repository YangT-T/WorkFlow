var app = new Vue({
    el:"#app",
    data:{
        propertyList:[],
        valueList:[],
        tep:String
        // deploymentId:String
    },
    created:function () {
        this.tep="请提交必填数据";
        //调用方法
        this.getAll();
    },
    methods:{
        getAll:function(){
            let deploymentId = $("#deploymentId").val();
            axios.get('/bpmn/initial/get?deploymentId='+deploymentId)
                .then(function(result){
                    console.log(result);
                    app.propertyList = result.data;
                    app.valueList = new Array(result.data.length);
                });
        },

        createInstance:function(){
            let deploymentId = $("#deploymentId").val();
            const params = {
                keys: this.propertyList,
                values: this.valueList
            };
            axios.post('/deployment/createInstanceWithMap?id='+deploymentId, {params})
                .then(function(result){
                    this.tep="创建成功";
                    window.alert("创建成功")
                    window.opener=null;
                    window.open('','_self');
                    window.close();
                });
        },
        getHistory:function (){
            axios.get('/history').then(function(result){
                location.href='/historyPage';
            });
        }
    }
});