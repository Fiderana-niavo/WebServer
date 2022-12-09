Le project ne marche que dans mozilla firefox 

->Le client tape l'url en tapant uniquement le nom du fichier. 
->Grace a la fonction "getUrlClient" je prends l'url contenant "GET" ou "POST" mais qui est different de "favicon.ico", dans un but d'obtenir la ressource demande par le client.
->Comme je l'ai mentionne un peu plus tot, on a besoin de la fonction "getUrlClient" qui retournera "GET /La ressourceDemande /Http 1.1",suivi de la fonction "getUrlEnd" qui va spliter notre url obtenu dans la fonction precedente. 
->Le variable obtenu par split(" ") dans la fonction "getUrlEnd" sera retourne directement si elle ne contient pas une "?", c'est a dire que une/des variable(s) a/ont ete envoye(s)
->dans ce dernier on a besoin de la fonction "getVariable" qui split("\\?")  et retourne la valeur apres le "?",c'est a dire que lq ou les variqbles envoye pare un formulaire  
-> la fonction "getHtmlTOPhp" permet  d'executer une fonction php grace a la fonction integrer de java RunTime
->Ainsi, On retourne le process en stream qui sera lu et retournera la versio  html du code php 
->Si le  fichier est de type html le serveur lit dans le ficier demander et retourne la version  String du fichier lu plar l'intermediaire de la fonction "getHtmlText"
 
--On a la fonction getExtension pour savoir si la ressource demande est de type php, sinon il sera de type html
--On peut envoyer une valeur d'une page a une autre par formulaire

Si la methode est de type get on prend la valeur de la variable envoye par formulaire grace a la fonction  "getVariable"
Si elle est de type post on prend la valeur en cherchant dans le header du client la valeur envoye par get
