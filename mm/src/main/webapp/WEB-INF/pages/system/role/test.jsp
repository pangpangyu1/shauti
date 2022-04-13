<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../../css/demo.css" type="text/css">
<link rel="stylesheet" href="../../../css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../../../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../../js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="../../../js/jquery.ztree.excheck-3.5.js"></script>
<SCRIPT type="text/javascript">
    var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
    var zNodes =[
        { id:2, pId:0, name:"test", checked:true, open:true},
        { id:21, pId:2, name:"test22222"},
        { id:22, pId:1, name:"test22222"}
    ];

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        var zTree = $.fn.zTree.getZTreeObj("treeDemo")
        zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" }
    });
</SCRIPT>
<ul id="treeDemo" class="ztree"></ul>
