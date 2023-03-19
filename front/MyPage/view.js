$(document).ready(function() {
    // 텝 메뉴 js 파일
    $('ul.tabs li').click(function(){
        var tab_id = $(this).attr('data-tab');

        $('ul.tabs li').removeClass('current');
        $('.tab-content').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    });

    let num = 1;

    $('#btn_add').click(function() {
        if(num < 6) {
            num++;
            let tabContent = $('#tab-1').clone().prop('id', 'tab-' + num);
        $('#dog_infos').append(tabContent);
        $('#tab-'+ num).attr('class', 'tab-content');	 		
        $('#tab-'+num+' input[name=dogName1]').attr('name', 'dogName'+num);
        $('#tab-'+num+' select[name=dogKind1]').attr('name', 'dogKind'+num);
        $('#tab-'+num+' select[name=dogSize1]').attr('name', 'dogSize'+num);
        $('#tab-'+num+' input[name=dogGender1]').attr('name', 'dogGender'+num);
        $('#tab-'+num+' input[name=neutered1]').attr('name', 'neutered'+num);
        $('#tab-'+num+' input[name=vaccine1]').attr('name', 'vaccine'+num);
        
        $('#tab_title'+(num - 1)).css('display', 'inline-block');
        } else {
            num = 6;
            alert('마지막 페이지입니다.')
        }
    });

    $('#btn_del').on('click', function() {
    
        if(num > 1) {
        $('div#tab-'+(num)).remove()
        $('#tab_title' + (num-1)).css('display', 'none');
        num--;
        } else {
            alert('더 이상 제거 할 수 없습니다.')
            num = 1;
        }
    });

});