function openModalBox(id) {
    const modal = document.getElementById(id);
    if (modal != null)
        modal.style.display = "block";
}

function closeModalBox(id) {
    const modal = document.getElementById(id);

    modal.style.display = "none";
}

let dropdownOn = false;

function getDropDown() {
    const dropDown = document.getElementById("dropdown-list")

    if (dropdownOn) {
        dropDown.style.display = "none";
        dropdownOn = false;
    } else {
        dropDown.style.display = "block";
        dropdownOn = true;
    }
}

function findLabelForControl(el) {
    const idVal = el.id;
    let labels = document.getElementsByTagName('label');
    for (let i = 0; i < labels.length; i++) {
        if (labels[i].htmlFor === idVal)
            return labels[i];
    }
}

function updateDropDownValue() {
    const dropDownLabel = document.getElementById("dropdown-label")
    dropDownLabel.innerHTML = '';

    const checkboxes = document.getElementsByClassName("checkbox-item")

    let checkbox;

    for (checkbox of checkboxes) {
        if (checkbox.checked) {
            console.log("checkbox id" + checkbox.id);
            const label = findLabelForControl(checkbox);
            if (dropDownLabel.innerHTML === '') {
                dropDownLabel.innerHTML += label.textContent;
            } else {
                dropDownLabel.innerHTML += ' - ' + label.textContent;
                console.log(label.textContent);
            }
        }
    }
}

function openProfile() {
    const dropDownProfile = document.getElementById("dropdown-profile");

    const status = dropDownProfile.style.display;
    
    if(status === "block"){
        dropDownProfile.style.display = "none";
    }
    else{
        dropDownProfile.style.display = "block";
    }
    
}