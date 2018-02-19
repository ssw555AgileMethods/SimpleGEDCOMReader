//read in file from Family.txt
const fs = require ('fs');

function getData (path) {
    if (!path) throw "Must provide path";
    let result ="";

    let tags = ['INDI', 'NAME', 'SEX', 'BIRT', 'DEAT', 'FAMC', 'FAMS', 'FAM', 'MARR', 
    'HUSB', 'WIFE', 'CHIL', 'DIV', 'DATE', 'HEAD', 'TRLR', 'NOTE']; //array of valid tags

    let file = fs.readFileSync(path, "utf-8"); //reads in entire file
    let fileLines = file.split('\n'); 
    for (let i=0; i<fileLines.length; i++) {
        console.log("-->"+fileLines[i]); //prints --> <input-line>
        result +="-->"+fileLines[i]+"\n";
        
        let lineData = fileLines[i].split(' ');
        let level = lineData[0];
        let tag = lineData[1]; //for normal, non INDI/FAM, lines        
        //console.log (tag);

        let line = fileLines[i].toString();
        //console.log (line);
        let index = line.indexOf( ' ', line.indexOf( ' ' ) + 1 );//get data after the second whitespace
        //console.log(index);
        let arguments=line.substr(index+1);//get data after the second whitespace
        //console.log (arguments);

        if (line == arguments) {//handle pesky 2 token things because I'm a bad coder
            let shortLine=arguments;
            level = shortLine.substring(0,1);
            //console.log (level);
            tag = shortLine.substring(2).trim(); //need to trim out whitespaces
            //console.log (tag);

            let valid = "N"; //default false
            if (tags.includes(tag)){//test validity
                valid = "Y";
            }
            console.log ("<--"+level+"|"+valid+"|"+tag);
            result +="<--"+level+"|"+valid+"|"+tag+"\n";
        } else {
            arguments = arguments.trim();//make sure whitespaces are gotten rid of
            if (arguments.includes("INDI") || arguments.includes("FAM")) {//handle INDI and FAM special cases
                tag = arguments; //the tag is the third token
                arguments = lineData[1];//and the arguments is the second token
            }

            let valid = "N"; //default false
            if (tags.includes(tag)){//test validity
                valid = "Y";
            }    
            console.log("<--"+level+"|"+tag+"|"+valid+"|"+arguments);
            result +="<--"+level+"|"+tag+"|"+valid+"|"+arguments+"\n";
        }
    }
    return result;
}

function main () {
    let data = getData ("./Family.txt");
    //console.log(data);
    fs.writeFileSync("./Output.txt", data);
}

main ();