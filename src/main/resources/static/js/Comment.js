function commentDataSend() {
    var data={
        text:$("#content").val(),
        writerId: $("#writerId").val(),
    };
    var messageDTO=data;
    $.ajax({
        url: window.location.href+"/comment",
        data: messageDTO,
        type:"POST",
    }).done(function (fragment) {
        $("#commentTable").replaceWith(fragment);
    });
}
