var app = new Vue({
    el:"#app",
    data:{
        processlist:[]
    },
    created:function () {
        //调用方法
        this.findAll();
    },
    methods:{
        findAll:function(){
            axios.get('/process/getAll')
                .then(function(result){
                    console.log(result);
                    app.processlist = result.data;
                    for (let i = 0; i < app.processlist.length; i++) {
                        app.processlist[i].startTime=new Date(app.processlist[i].startTime);
                    }
                });
        },
    }
});