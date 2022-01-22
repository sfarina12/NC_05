/**
 * La funzione descritta permette l'inizializzazione di uno slider secondo la documentazione della componente off-the-shelf 'slick'
 * 
 * @author Simone Farina
 */
$(document).ready(function(){
    $('.new-books-container').slick({
            slidesToShow: 3,
            slidesToScroll: 1,
            autoplay: true,
            autoplaySpeed: 1000,       
            prevArrow:"<img class='a-left control-c prev slick-prev' src='./tools/images/icone/arrow-left.png' alt='freccia a sinistra'>",
            nextArrow:"<img class='a-right control-c next slick-next' src='./tools/images/icone/arrow-right.png' alt='freccia a destra'>"                     
    });
  });