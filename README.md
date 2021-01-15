# project_JAVA_Adoption

Aplicatia realizata in cadrul proiectului reprezinta un instrument pentru gestionarea activitatilor din cadrul unui centru de adoptii de animale si este divizata in doua componenete: baza de date(MySQL), backend(Spring Boot).

Astfel, admin-ul este persoana care intermediaza o adoptie, nu pot exista doi admini cu acelasi email.

Person este modelul ce defineste persoana ce doreste sa adopte un animal, de asemenea nu pot exista doua personae cu acelasi email.

In cadrul modelului Animal am definit caracteristicile unui animal, nu pot exista in baza de date doua animale cu acelasi nume, iar adoptia fiecarui animal se desfasoara in functie de statusul acestora: IN_CENTER si WANTED_FOR_Adoption. De asemenea, in cazul in care animalul este inca in centru si nu are un request de adoptie va avea ca adoption_date valoarea null, insa in mometul in care este adoptat I se va atribui obligatoriu si o data a adoptiei.

Astfel, un animal poate fi adoptat doar daca are ca status IN_CENTER, daca persoana care doreste sa il adopte exista in baza de date si, de asemenea, daca adminul exista in baza de date.

In acelasi timp, fiecare animal al centrului poate dispune de cate o fisa medicala care detaliaza bolile sau tratamentele la care a fost supus. Aceasta fisa medicala este manageriata de un veterinar. Nu pot exista doi veterinari cu acelasi numar al licentei. 
