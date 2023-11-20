var app = new Vue({
    el:"#app",
    data:{
        tasklist:[]
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
                    app.tasklist = result.data;
                });
        },
    }
});