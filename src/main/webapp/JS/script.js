function validateString(str,exp){
    return exp.test(str);
}
function showError(id,msg) {
    document.getElementById(id).innerHTML=msg;
    document.getElementById(id).style.visibility='visible';
}

function imageValidate(fileInput,errorid){
    if(fileInput.files.length===0){
        showError(errorid,'* please select image file');
        return false;
    }

    var filePath = fileInput.files[0].name;
    
    // Allowing file type
    var allowedExtensions =
    /(\.jpg|\.jpeg|\.png|\.gif)$/;
    
    if (!allowedExtensions.test(filePath)) {
        showError(errorid,'Invalid file type');
        return false;
    }else{
        let s=Math.round(fileInput.files[0].size/1024);
        if(s>1024){
            showError(errorid,'File is too big it should be < 1mb');
            return false;
        }else{
            document.getElementById(errorid).style.visibility='hidden';
            document.getElementById('image').value=filePath;
            return true;
        }
    }
}

function formv(){
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
    // return flag;
    //validate image
    if(!imageValidate(image,'imageerror')){
        flag=false;
    }
       return flag;

       
}