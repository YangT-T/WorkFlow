var app = new Vue({
    el:"#app",
    data:{
        propertyList:[],
        valueList:[],
        tep:String
    },
    created:function () {
        this.tep="请提交必填数据";
        //调用方法
        this.getAll();
    },
    methods:{
        getAll:function(){
            let taskId=$("#taskId").val();
            axios.get('/bpmn/Flow/get?taskId='+taskId)
                .then(function(result){
                    console.log(result);
                    app.propertyList = result.data;
                });
        },
        completeTask:function(){
            let taskId=$("#taskId").val();
            const params = {
                keys: this.propertyList,
                values: this.valueList
            };
            axios.post('/task/completeWithMap?id='+taskId, {params})
                .then(function(result){
                    window.alert("任务完成")
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