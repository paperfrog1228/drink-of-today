function commentDataSend() {
    var text=$("#content").val();
    if(! text){
        alert("내용을 입력하세요!");
        return;
    }
    var data={
        text: text,
        memberId: $("#memberId").val(),
    };
    var messageDTO=data;
    $.ajax({
        url: window.location.href.split('?')[0]+"/comment",
        data: messageDTO,
        type:"POST",
    }).done(function (fragment) {
        $("#content").val('');
        $("#commentTable").replaceWith(fragment);
    });
}
function commentDelete(value) {
    var id=value;
    var data={
        commentId: id,
        memberId: $("#memberId").val(),
    };
    var messageDTO=data;
    $.ajax({
        url: window.location.href+"/comment",
        cache : "false",
        data: messageDTO,
        type:"DELETE",
    }).done(function (fragment) {
        $("#content").val('');
        $("#commentTable").replaceWith(fragment);
    });
}