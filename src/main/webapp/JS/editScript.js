

function formv2(){
    //event.preventDefault();
    let nameExp=/^[a-zA-Z ]+$/;
    let numExp=/^[0-9]+$/;
    
    let title=document.getElementById('title').value;
    let quantity=document.getElementById('quantity').value;
    let size=document.getElementById('size').value;
    let image=document.getElementById('imgfile');
    let flag=true;
    
    console.log("inside form");

    //validate title
    if (validateString(title,nameExp)) {
        document.getElementById('titleerror').style.visibility='hidden';
    }else{
        showError('titleerror','* invalid title');
        flag=false;
    }
    
    //validate quantity
    if (validateString(quantity,numExp)) {
        document.getElementById('quantityerror').style.visibility='hidden';
    }else{
        showError('quantityerror','* invalid quantity');
        flag=false;
    }
    
    //validate size
    if (validateString(size,numExp)) {
        document.getElementById('sizeerror').style.visibility='hidden';
    }else{
        showError('sizeerror','* Invalid size');
        flag=false;
    }

    // console.log(flag);
    // console.log(image.files.length);
    	return flag;
}