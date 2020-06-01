/* Click color change image product */
jQuery(document).ready(function($) {
  jQuery('.engoj_select_color a').each(function(){
    jQuery(this).on("click",function(){
      var engoImage = jQuery(this).data('engojvariant-img');
      jQuery(this).parents('.engoj_grid_parent').find('.engoj_find_img img').attr({ src: engoImage }); 
      return false;
    });
  });
});

jQuery(document).ready(function($) {
  "use strict";

  
  function jsProdDetails(){
    $('.js_prod_detail').slick({
      dots: true,
      arrows:true,
      infinite: false,
      speed: 300,
      autoplay:false,
      slidesToShow: 1,
      slidesToScroll: 1,
      nextArrow:'<button type="button" class="next-slide"><svg version="1.1" baseProfile="basic" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="1000px" height="500px" viewBox="0 0 1000 500" xml:space="preserve"> <g id="Forma_1_1_"> <g id="Forma_1"> <path fill-rule="evenodd" clip-rule="evenodd" d="M834.107,232.981L618.365,17.218c-9.3-9.624-24.779-9.624-34.405,0 c-9.299,9.303-9.299,24.778,0,34.059l174.447,174.441H182.759c-13.42,0.021-24.086,10.686-24.086,24.108 c0,13.417,10.666,24.436,24.086,24.436h575.648L583.96,448.375c-9.299,9.63-9.299,25.12,0,34.401 c9.626,9.632,25.128,9.632,34.405,0l215.742-215.742C843.733,257.739,843.733,242.263,834.107,232.981z"/> </g> </g> <g id="Forma_1_copy_1_"> </g> </svg></button>',
      prevArrow:'<button type="button" class="prev-slide"><svg version="1.1" baseProfile="basic" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="1000px" height="500px" viewBox="0 0 1000 500" xml:space="preserve"> <g id="Forma_1_1_"> </g> <g id="Forma_1_copy_1_"> <g id="Forma_1_copy"> <path fill-rule="evenodd" clip-rule="evenodd" d="M811.881,225.728H245.96L417.457,51.275c9.143-9.269,9.143-24.751,0-34.055 c-9.463-9.627-24.679-9.627-33.821,0l-212.1,215.762c-9.462,9.282-9.462,24.765,0,34.054l212.1,215.742 c9.12,9.627,24.358,9.627,33.821,0c9.143-9.276,9.143-24.772,0-34.4L245.96,274.265h565.921c13.192,0,23.68-11.014,23.68-24.434 C835.561,236.411,825.073,225.749,811.881,225.728z"/> </g> </g> </svg></button>',
    });

  }
  
  function jsProdRelate(){
    $('.js_product_related').slick({
      dots: true,
      arrows:false,
      infinite: false,
      speed: 300,
      autoplay:false,
      slidesToShow: 4,
      slidesToScroll: 1,
      nextArrow:'<button type="button" class="next-slide"><svg version="1.1" baseProfile="basic" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="1000px" height="500px" viewBox="0 0 1000 500" xml:space="preserve"> <g id="Forma_1_1_"> <g id="Forma_1"> <path fill-rule="evenodd" clip-rule="evenodd" d="M834.107,232.981L618.365,17.218c-9.3-9.624-24.779-9.624-34.405,0 c-9.299,9.303-9.299,24.778,0,34.059l174.447,174.441H182.759c-13.42,0.021-24.086,10.686-24.086,24.108 c0,13.417,10.666,24.436,24.086,24.436h575.648L583.96,448.375c-9.299,9.63-9.299,25.12,0,34.401 c9.626,9.632,25.128,9.632,34.405,0l215.742-215.742C843.733,257.739,843.733,242.263,834.107,232.981z"/> </g> </g> <g id="Forma_1_copy_1_"> </g> </svg></button>',
      prevArrow:'<button type="button" class="prev-slide"><svg version="1.1" baseProfile="basic" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="1000px" height="500px" viewBox="0 0 1000 500" xml:space="preserve"> <g id="Forma_1_1_"> </g> <g id="Forma_1_copy_1_"> <g id="Forma_1_copy"> <path fill-rule="evenodd" clip-rule="evenodd" d="M811.881,225.728H245.96L417.457,51.275c9.143-9.269,9.143-24.751,0-34.055 c-9.463-9.627-24.679-9.627-33.821,0l-212.1,215.762c-9.462,9.282-9.462,24.765,0,34.054l212.1,215.742 c9.12,9.627,24.358,9.627,33.821,0c9.143-9.276,9.143-24.772,0-34.4L245.96,274.265h565.921c13.192,0,23.68-11.014,23.68-24.434 C835.561,236.411,825.073,225.749,811.881,225.728z"/> </g> </g> </svg></button>',
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            dots: false, 
            arrows: true
          }
        }
      ]
    });

  }
  
  function jsCollection(){
    $('.js_collection').slick({
      dots: true,
      arrows:false,
      infinite: false,
      speed: 300,
      autoplay:false,
      slidesToShow: 3,
      slidesToScroll: 1,
      nextArrow:'<button type="button" class="next-slide"><svg version="1.1" baseProfile="basic" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="1000px" height="500px" viewBox="0 0 1000 500" xml:space="preserve"> <g id="Forma_1_1_"> <g id="Forma_1"> <path fill-rule="evenodd" clip-rule="evenodd" d="M834.107,232.981L618.365,17.218c-9.3-9.624-24.779-9.624-34.405,0 c-9.299,9.303-9.299,24.778,0,34.059l174.447,174.441H182.759c-13.42,0.021-24.086,10.686-24.086,24.108 c0,13.417,10.666,24.436,24.086,24.436h575.648L583.96,448.375c-9.299,9.63-9.299,25.12,0,34.401 c9.626,9.632,25.128,9.632,34.405,0l215.742-215.742C843.733,257.739,843.733,242.263,834.107,232.981z"/> </g> </g> <g id="Forma_1_copy_1_"> </g> </svg></button>',
      prevArrow:'<button type="button" class="prev-slide"><svg version="1.1" baseProfile="basic" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="1000px" height="500px" viewBox="0 0 1000 500" xml:space="preserve"> <g id="Forma_1_1_"> </g> <g id="Forma_1_copy_1_"> <g id="Forma_1_copy"> <path fill-rule="evenodd" clip-rule="evenodd" d="M811.881,225.728H245.96L417.457,51.275c9.143-9.269,9.143-24.751,0-34.055 c-9.463-9.627-24.679-9.627-33.821,0l-212.1,215.762c-9.462,9.282-9.462,24.765,0,34.054l212.1,215.742 c9.12,9.627,24.358,9.627,33.821,0c9.143-9.276,9.143-24.772,0-34.4L245.96,274.265h565.921c13.192,0,23.68-11.014,23.68-24.434 C835.561,236.411,825.073,225.749,811.881,225.728z"/> </g> </g> </svg></button>',
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            dots: false, 
            arrows: true
          }
        }
      ]
    });

  }
  
   function slideProductDetail() {
    $('.js_prod_main').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      arrows: false,
      dot: false,
      fade: false,
      infinite: false,
//       prevArrow: '<button type="button" class="slick-brand-prev ti-angle-left"></button>',
//       nextArrow: '<button type="button" class="slick-brand-next ti-angle-right"></button>',
      asNavFor: '.js_prod_sub'
    });
    $('.js_prod_sub').slick({
      slidesToShow: 3,
      slidesToScroll: 1,
      asNavFor: '.js_prod_main',
      dots: false,
      arrows: false,
      infinite: false,
      vertical: true,
      verticalSwiping: true,
      focusOnSelect: true,
      responsive: [
        {
          breakpoint: 1200,
          settings: {
            vertical: true,
            infinite: false,
          }
        },
        {
          breakpoint: 768,
          settings: {
            vertical: true,
            infinite: false,
          }
        },
        
        {
          breakpoint: 575,
          settings: {
            vertical: false,
            infinite: false,
          }
        }
      ]
    });
  
   }
  function slideProductDetailBottom() {
    $('.js_prod_main_bot').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      arrows: false,
      dot: false,
      fade: false,
      infinite: false,
    });
  
   }
  
  function sizeGuide(){
    $('.btn_size_guide').on('click',function(){
      $('.content_size_guide').addClass('active');
      $('.js_sticky').removeClass('sticky_content');
      $('.overlay').addClass('active');
    });
    $('.overlay').on('click',function(){
      $('.content_size_guide').removeClass('active');
      $('.overlay').removeClass('active');
    });
    $('.close_size_guide').on('click',function(){
      $('.js_sticky').addClass('sticky_content');
      $('.content_size_guide').removeClass('active');
      $('.overlay').removeClass('active');
    });
  }
  
  function sortLayout() {
      if ($(window).width() < 1300) {
        $('.size_3').addClass('active');
        $('.size_4').removeClass('active');
        $('.size_5').addClass('d-none');
        $('.size_6').addClass('d-none');
      }
      
      if ($(window).width() >= 1300) {
        $('.size_3').addClass('active');
        $('.size_4').removeClass('active');
        $('.size_5').removeClass('d-none');
        $('.size_6').removeClass('d-none');
      }
    
      if ($(window).width() < 768) {
        $('.size_2').addClass('active');
        $('.size_4').removeClass('active');
        $('.size_5').addClass('d-none');
        $('.size_6').addClass('d-none');
        $('.size_4').addClass('d-none');
        $('.size_3').addClass('d-none');
      }
      $(window).resize(function(){
        if ($(window).width() < 1300) {
          $('.size_3').addClass('active');
          $('.size_4').removeClass('active');
          $('.size_5').addClass('d-none');
          $('.size_6').addClass('d-none');
        }

        if ($(window).width() >= 1300) {
          $('.size_3').addClass('active');
          $('.size_4').removeClass('active');
          $('.size_5').removeClass('d-none');
          $('.size_6').removeClass('d-none');
        }

        if ($(window).width() < 768) {
          $('.size_2').addClass('active');
          $('.size_4').removeClass('active');
          $('.size_5').addClass('d-none');
          $('.size_6').addClass('d-none');
          $('.size_4').addClass('d-none');
          $('.size_3').addClass('d-none');
        }
      });
     
      $('.size_6').on('click', function() {
        $('.js_size_prod').removeClass('col-lg-1 col-lg-3 col-lg-4 col-lg-5 col-lg-6 col-lg-7 col-lg-8 col-lg-9 col-lg-10 col-lg-11 col-lg-12 col-lg-2dot4 col-md-1 col-md-2 col-md-3 col-md-4 col-md-5 col-md-6 col-md-7 col-md-8 col-md-9 col-md-10 col-md-11 col-md-12');
        $('.js_size_prod').addClass(' col-lg-2');
        $(this).addClass('active');
        $('.size_5').removeClass('active');
        $('.size_4').removeClass('active');
        $('.size_3').removeClass('active');
      });	
	 	
      $('.size_5').on('click', function() {
        $('.js_size_prod').removeClass('col-lg-1 col-lg-2 col-lg-3 col-lg-4 col-lg-5 col-lg-6 col-lg-7 col-lg-8 col-lg-9 col-lg-10 col-lg-11 col-lg-12 col-lg-2dot4 col-md-1 col-md-2 col-md-3 col-md-4 col-md-5 col-md-6 col-md-7 col-md-8 col-md-9 col-md-10 col-md-11 col-md-12');
        $('.js_size_prod').addClass('col-lg-2dot4');
        $(this).addClass('active');
        $('.size_6').removeClass('active');
        $('.size_4').removeClass('active');
        $('.size_3').removeClass('active');
      });
      $('.size_4').on('click', function() {
        $('.js_size_prod').removeClass('col-lg-1 col-lg-2 col-lg-3 col-lg-4 col-lg-5 col-lg-6 col-lg-7 col-lg-8 col-lg-9 col-lg-10 col-lg-11 col-lg-12 col-lg-2dot4 col-md-1 col-md-2 col-md-3 col-md-4 col-md-5 col-md-6 col-md-7 col-md-8 col-md-9 col-md-10 col-md-11 col-md-12');
        $('.js_size_prod').addClass('col-md-3');
        $(this).addClass('active');
        $('.size_5').removeClass('active');
        $('.size_6').removeClass('active');
        $('.size_3').removeClass('active');
        $('.size_2').removeClass('active');
      });
      $('.size_3').on('click', function() {
        $('.js_size_prod').removeClass('col-lg-1 col-lg-2 col-lg-3 col-lg-4 col-lg-5 col-lg-6 col-lg-7 col-lg-8 col-lg-9 col-lg-10 col-lg-11 col-lg-12 col-lg-2dot4 col-md-1 col-md-2 col-md-3 col-md-4 col-md-5 col-md-6 col-md-7 col-md-8 col-md-9 col-md-10 col-md-11 col-md-12');
        $('.js_size_prod').addClass('col-md-4');
        $(this).addClass('active');
        $('.size_5').removeClass('active');
        $('.size_4').removeClass('active');
        $('.size_6').removeClass('active');
        $('.size_2').removeClass('active');
      });
      $('.size_2').on('click', function() {
        $('.js_size_prod').removeClass('col-lg-1 col-lg-2 col-lg-3 col-lg-4 col-lg-5 col-lg-6 col-lg-7 col-lg-8 col-lg-9 col-lg-10 col-lg-11 col-lg-12 col-lg-2dot4 col-md-1 col-md-2 col-md-3 col-md-4 col-md-5 col-md-6 col-md-7 col-md-8 col-md-9 col-md-10 col-md-11 col-md-12 col-12');
        $('.js_size_prod').addClass('col-6');
        $(this).addClass('active');
        $('.size_3').removeClass('active');
        $('.size_4').removeClass('active');
        $('.size_1').removeClass('active');
      });
    
      $('.size_1').on('click', function() {
        $('.js_size_prod').removeClass('col-lg-1 col-lg-2 col-lg-3 col-lg-4 col-lg-5 col-lg-6 col-lg-7 col-lg-8 col-lg-9 col-lg-10 col-lg-11 col-lg-12 col-lg-2dot4 col-md-1 col-md-2 col-md-3 col-md-4 col-md-5 col-md-6 col-md-7 col-md-8 col-md-9 col-md-10 col-md-11 col-md-12 col-6');
        $('.js_size_prod').addClass('col-12');
        $(this).addClass('active');
        $('.size_3').removeClass('active');
        $('.size_4').removeClass('active');
        $('.size_2').removeClass('active');
      });
    	
   	    
    	
    }
  
  function changeProdListGrid(){
    $('.prod_grid').on('click',function(){
      $('.product-grid-view').addClass('active');
      $('.product-list-view').removeClass('active');
      $(this).addClass('active');
      $('.prod_list').removeClass('active');
      $('.prod_per').removeClass('active');

    });
    $('.prod_list').on('click',function(){
      $('.product-grid-view').removeClass('active');
      $('.product-list-view').addClass('active');
      $(this).addClass('active');
      $('.prod_grid').removeClass('active');
      $('.prod_per').addClass('active');
    });
  }
  
  function FilterPushLeft(){
    $('.js_filter').on('click',function(){
      $('.filter-to-left').toggleClass('active');
      $('.overlay-filter').addClass('active');    
    });
    
    
    $('.close_filter').on('click',function(){
      $('.filter-to-left').removeClass('active');
      $('.overlay-filter').removeClass('active'); 
    });
    $('.overlay-filter').on('click',function(){
      $('.filter-to-left').removeClass('active');
      $('.overlay-filter').removeClass('active'); 
    });
    
 
  }
  
  function heighthdSticky(){
    
    $('.detail-info').css({'top':function(){
        return $('.js_height_hd').outerHeight() + 10;
      }})
  }

  function listcollection(){
    $('.js_list_col').slick({
      dots: false,
      arrows:false,
      infinite: false,
      speed: 300,
      autoplay:false,
      slidesToShow: 5,
      slidesToScroll: 1,
      responsive: [
        {
          breakpoint: 1200,
          settings: {
            slidesToShow: 4,
          }
        },
        {
          breakpoint: 768,
          settings: {
            slidesToShow: 3,
          }
        },
        
        {
          breakpoint: 575,
          settings: {
            slidesToShow: 3,
          }
        }
      ]
    });

  }
  
  
  jsProdDetails();
  listcollection()
  jsProdRelate();
  slideProductDetail();
  sizeGuide();
  slideProductDetailBottom();
  sortLayout();
  changeProdListGrid();
  FilterPushLeft();
  jsCollection();
  heighthdSticky();



});

jQuery(document).ready(function($) {
  $('[data-toggle="tooltip"]').tooltip(); 
});



jQuery(document).ready(function($) {
  "use strict";

  function brandv1() {
    $('.jsbrand_list_v1').slick({
      dots: false,
      arrows:true,
      speed: 300,
      slidesToShow: 5,
      slidesToScroll: 1,
      prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i></button>',
      nextArrow:'<button type="button" class="next-slide"><i class="fa fa-angle-right"></i></button>',
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 2
          }
        }
      ]
    });
  }

  function brandv2() {
    $('.jsbrand_list_v2').slick({
      dots: false,
      arrows:true,
      speed: 300,
      slidesToShow: 5,
      slidesToScroll: 1,
      prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i></button>',
      nextArrow:'<button type="button" class="next-slide"><i class="fa fa-angle-right"></i></button>',
      responsive: [
        {
          breakpoint: 1367,
          settings: {
            slidesToShow: 4
          }
        },
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 2
          }
        }
      ]
    });
  }

  function servicev1(){
    $('.slickjs-services-v1').slick({
      dots: false,
      arrows:false,
      infinite: false,
      speed: 300,
      autoplay:true,
      slidesToShow: 3,
      slidesToScroll: 1,
      prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i></button>',
      nextArrow:'<button type="button" class="next-slide"><i class="fa fa-angle-right"></i></button>',
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 1
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1
          }
        }
      ]
    });

  }
  
  function servicev2(){
    $('.slickjs-services-v2').slick({
      dots: false,
      arrows:false,
      infinite: false,
      speed: 300,
      autoplay:true,
      slidesToShow: 3,
      slidesToScroll: 1,
      prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i></button>',
      nextArrow:'<button type="button" class="next-slide"><i class="fa fa-angle-right"></i></button>',
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 992,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 1
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1
          }
        }
      ]
    });

  }

  function producth1banner(){
    $('.slick_product_h1_banner').slick({
      arrows: true,
      infinite: false,
      dots: false ,
      speed: 300,
      slidesToShow: 2,
      slidesToScroll: 1,
      prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i> </button>',
      nextArrow:'<button type="button" class="next-slide"> <i class="fa fa-angle-right"></i></button>',
       responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2
          }
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 2
          }
        }
      ]
    });
  }

  function productnewh4(){
    $('.slick_product_new-h4').slick({
      arrows: true,
      infinite: false,
      dots: false ,
      speed: 300,
      slidesToShow: 4,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 2000,
      prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i> </button>',
      nextArrow:'<button type="button" class="next-slide"> <i class="fa fa-angle-right"></i></button>',
      responsive: [
        {
          breakpoint: 1200,
          settings: {
            slidesToShow: 3
          }
        },
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3,
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 2,
          }
        }
      ]
    });
  }

  function jsBlogv3(){
    $('.js-blog-v3').slick({
      infinite: true,
      slidesToShow: 3,
      slidesToScroll: 3,
      dots: true,
      arrows: true,
      prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i> </button>',
      nextArrow:'<button type="button" class="next-slide"> <i class="fa fa-angle-right"></i></button>',
      responsive: [
        {
          breakpoint: 1200,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 1,
            infinite: true,
            dots: false,
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1,
            dots: false,
          }
        },


      ]
        });
        }

  function Blogv1(){
          $('.js-blog-v1').slick({
          infinite: true,
          slidesToShow: 3,
          slidesToScroll: 3,
          dots: true,
          arrows: true,
          prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i> </button>',
          nextArrow:'<button type="button" class="next-slide"> <i class="fa fa-angle-right"></i></button>',
          responsive: [
          {
            breakpoint: 1500,
            settings: {
              slidesToShow: 3,
              slidesToScroll: 1,
              arrow: true,
              dots: false,
            }
          },
          {
            breakpoint: 992,
            settings: {
              slidesToShow: 2,
              slidesToScroll: 1,
              arrows: true,
              dots: false,
            }
          },
         {
            breakpoint: 575,
            settings: {
              slidesToShow: 1,
              slidesToScroll: 1,
              arrows: true,
              dots: false,
            }
          },


        ]

          });
        }

  function Blogv2(){
    $('.js-blog-v2').slick({
      infinite: true,
      slidesToShow: 3,
      slidesToScroll: 1,
      dots: false,
      arrows: true,
      prevArrow:'<button type="button" class="prev-slide"><i class="fa fa-angle-left"></i> </button>',
      nextArrow:'<button type="button" class="next-slide"> <i class="fa fa-angle-right"></i></button>',
      responsive: [
        {
          breakpoint: 1200,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 1
          }
        },
        {
          breakpoint: 992,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 1
          }
        },
        {
          breakpoint: 600,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1
          }
        },


      ]
        });
        }
        
  function slidehome5(){
    $('.slick-side-h5').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 3500,
      dots: true,
      arrows: false,
      fade: true, 
      responsive: [

        {
          breakpoint: 1200,
          settings: {
            infinite: true,
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint: 1024,
          settings: {
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint:600,
          settings: {
            dots: true ,
            arrows: false,
            autoplay: true,
          }
        }
      ]
    });
  }
        
  function slidehome4(){
    $('.slick-side-h4').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 3500,
      dots: true,
      arrows: false,
      fade: true, 
      responsive: [

        {
          breakpoint: 1200,
          settings: {
            infinite: true,
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint: 1024,
          settings: {
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint:600,
          settings: {
            dots: true ,
            arrows: false,
          }
        }
      ]
    });
  }
        
  function slidehome3(){
    $('.slick-side-h3').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 3500,
      dots: true,
      arrows: false,
      fade: true, 
      responsive: [

        {
          breakpoint: 1200,
          settings: {
            infinite: true,
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint: 1024,
          settings: {
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint:600,
          settings: {
            dots: true ,
            arrows: false,
            autoplay: true,
          }
        }
      ]
    });
  }

  function slidehome2(){
    $('.slick-side-h2').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 3500,
      dots: true,
      arrows: false,
      fade: true, 
      responsive: [

        {
          breakpoint: 1200,
          settings: {
            infinite: true,
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint: 1024,
          settings: {
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint:600,
          settings: {
            dots: true ,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 3500
          }
        }
      ]
    });
  }

  function slidehome1(){
    $('.slick-side-h1').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 3500,
      dots: true,
      arrows: false,
      fade: true, 
      responsive: [

        {
          breakpoint: 1200,
          settings: {
            infinite: true,
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint: 1024,
          settings: {
            dots: true ,
            arrows: false
          }
        },
        {
          breakpoint:600,
          settings: {
            dots: true ,
            arrows: false,
            autoplay: true,
          }
        }
      ]
    });
  }  
  
  function promo(){
    $('.close_promo_topbar').on('click',function(){
      $('.promo_topbar').addClass("active");
    });
  }
  
  function search(){
    $('.js-search-destop').on('click', function() {
      $('.js-box-search').addClass('active');
      $('.bg_search_box').addClass('active');
      $('body').addClass('activedestop');
    });
    $('.js-drawer-close').on('click', function() {
      $('.js-box-search').removeClass('active');
      $('.bg_search_box').removeClass('active');
      $('body').removeClass('activedestop');
    });
    $('.bg_search_box').on('click', function() {
      $('.js-box-search').removeClass('active');
      $('.bg_search_box').removeClass('active');
      $('body').removeClass('activedestop');
    });
  }
  
  function minicart(){
    $('.js-call-minicart').on('click', function() {
      $('.js-minicart').addClass('active');
      $('.bg-minicart').addClass('active');
      $('.contentbody').addClass('activecart');
      $('body').addClass('cartover');

    });
    $('.close-mini-cart').on('click', function() {
      $('.js-minicart').removeClass('active');
      $('.bg-minicart').removeClass('active');
      $('.contentbody').removeClass('activecart');
      $('body').removeClass('cartover');

    });
    $('.bg-minicart').on('click', function() {
      $('.js-minicart').removeClass('active');
      $('.bg-minicart').removeClass('active');
      $('.contentbody').removeClass('activecart');
      $('body').removeClass('cartover');

    });
  
  }

  function menudestopscroll1() {
    var $nav = $(".jsheader1");
    $nav.removeClass('menu_scroll_v1');

    $(document).scroll(function() {

      $nav.toggleClass('menu_scroll_v1', $(this).scrollTop() > $nav.height());
    });
  }  
  
  function menudestopscroll2() {
    var $nav = $(".jsheader2");
    $nav.removeClass('menu_scroll_v2');

    $(document).scroll(function() {

      $nav.toggleClass('menu_scroll_v2', $(this).scrollTop() > $nav.height());
    });
  }
  
  function menudestopscroll3() {
    var $nav = $(".jsheader3");
    $nav.removeClass('menu_scroll_v3');

    $(document).scroll(function() {

      $nav.toggleClass('menu_scroll_v3', $(this).scrollTop() > $nav.height());
    });
  }
  
  function menudestopscroll4() {
    var $nav = $(".jsheader4");
    $nav.removeClass('menu_scroll_v4');

    $(document).scroll(function() {

      $nav.toggleClass('menu_scroll_v4', $(this).scrollTop() > $nav.height());
    });
  }
  
  function menudestopscroll5() {
    var $nav = $(".jsheader5");
    $nav.removeClass('menu_scroll_v5');

    $(document).scroll(function() {

      $nav.toggleClass('menu_scroll_v5', $(this).scrollTop() > $nav.height());
    });
  }
  
  function menudestopscroll6() {
    var $nav = $(".jsheader6");
    $nav.removeClass('menu_scroll_v6');

    $(document).scroll(function() {

      $nav.toggleClass('menu_scroll_v6', $(this).scrollTop() > $nav.height());
    });
  }
  
  function menuhorizon() {
    
    $('.js-model_menu').on('click', function() {
      $('.js-horizon-menu').addClass('active');
      $('.js-bg-horizon-menu').addClass('active');
      $('body').addClass('cartover');
    });
    
    $('.js_close-menu-horizon').on('click', function() {
      $('.js-horizon-menu').removeClass('active');
      $('.js-bg-horizon-menu').removeClass('active');
      $('body').removeClass('cartover');
    });
    
    $('.js-bg-horizon-menu').on('click', function() {
      $('.js-horizon-menu').removeClass('active');
      $('.js-bg-horizon-menu').removeClass('active');
      $('body').removeClass('cartover');
    });

  }
  
  function jsmenuiconh(){
    $('.js_icon_horizon-menu').on( 'click', function() {
      $(this).toggleClass('active');
    });
  }
  
  function jscalllogindestop(){
    $('.js-call-popup-login').on( 'click', function() {
      $('.js-poup-login-destop').addClass('active');
      $('.js-bg-login-popup').addClass('active');
    });
    $('.js-eveland-close-login').on( 'click', function() {
      $('.js-poup-login-destop').removeClass('active');
      $('.js-bg-login-popup').removeClass('active');
    });
    $('.js-bg-login-popup').on( 'click', function() {
      $('.js-poup-login-destop').removeClass('active');
      $('.js-bg-login-popup').removeClass('active');
    });
  }
  
  function jsBackLogin(){
     $('.jsCreate_account').on('click', function() {
        $('.form_register').show();
        $('.formlogin').hide();    
    });
     $('.tab_navar_right').on('click', function() {
        $('.formlogin').show();
        $('.form_register').hide();    
    });

     $('.jsBack_login').on('click', function() {
        $('.formlogin').show();
        $('.form_register').hide();        
    });
     $('.jsacount_destop').on('click', function() {
        $('.formlogin').show();
        $('.form_register').hide();        
    });
}
  
 

  function jslogindestop(){
    $('.jsCreate_account').on('click', function() {
      $('.form_register-destop').show();
      $('.formlogin-destop').hide();    
    });
    $('.js-call-popup-login').on('click', function() {
      $('.formlogin-destop').show();
      $('.form_register-destop').hide();    
    });

    $('.jsBack_login').on('click', function() {
      $('.formlogin-destop').show();
      $('.form_register-destop').hide();        
    });
    $('.jsacount_destop').on('click', function() {
      $('.formlogin-destop').show();
      $('.form_register-destop').hide();        
    });
  }
  
  
  
  
  function mobilescroll() {
    var $nav = $(".jsmenumobile");
    $nav.removeClass('menu_mobilescroll');

    $(document).scroll(function() {

        $nav.toggleClass('menu_mobilescroll', $(this).scrollTop() > $nav.height());
    });
}
  
  function menumobile() {
    $('.menuleft').on('click', function() {
      $(this).toggleClass('active');
      $('.box_contentmenu').toggleClass('active');     
      $('.box_contentmenu_background').toggleClass('active');     
    });
    $('.box_contentmenu_background').on('click', function() {
      $(this).removeClass('active');
      $('.box_contentmenu').removeClass('active');
      $('.menuleft').removeClass('active');
      $('.box_contentmenu_background').removeClass('active');
    });
    $('.js-eveland-close').on('click', function() {
      $(this).removeClass('active');
      $('.box_contentmenu').removeClass('active');
      $('.menuleft').removeClass('active');
      $('.box_contentmenu_background').removeClass('active');
    });
  }
  
  
  function popup(){
    $('.jsclosepoup').on('click', function() {
      $('.jsengo_popup').addClass('d-none');

    });
  }
  function cookiepopup(){
    if (!jQuery.cookie('brilliant_pop_newletter') || jQuery.cookie('brilliant_pop_newletter') == null) {    
      jQuery(window).load(function() {
        setTimeout($('.jsengo_popup').show(), 300 * 1000);
        jQuery('.jsclosepoup').click(function(e) {
          e.preventDefault();
          jQuery('.jsengo_popup').hide(); 
          jQuery.cookie('brilliant_pop_newletter', '1', {expires: 1, path:'/', domain: '' });
                                                        });
        });
      } else {
                          $('.jsengo_popup').remove();
    }
  }
  
  
  // Scroll to TOP
  function totop(){
  	 var back_to_top = $('#back-to-top');
    if (back_to_top.length) {
        var scrollTrigger = 100, // px
            backToTop = function() {
                var scrollTop = $(window).scrollTop();
                if (scrollTop > scrollTrigger) {
                    back_to_top.addClass('show');
                } else {
                    back_to_top.removeClass('show');
                }
            };
        $(window).on('scroll', function() {
            backToTop();
        });
        back_to_top.on('click', function(e) {
            e.preventDefault();
            $('html,body').animate({
                scrollTop: 0
            }, 700);
        });
    }
  }
  function slidehome5() {
    $('.js-slideshow-v5').slick({
      slidesToShow: 1,
      slidesToScroll: 1,
      infinite: true,
      arrows: false,
      dots: true,
      fade: true
    });

    $('.js-slideshow-v5 .slideshow-content').eq(0).addClass('active');

    $('.js-slideshow-v5').on('beforeChange', function (event, slick, currentSlide, nextSlide) {
      var mySlideNumber = nextSlide;
      var prev = currentSlide;
      $('.section-slideshow-v5 .slideshow-content').eq(mySlideNumber).addClass('active');

      $('.section-slideshow-v5 .slideshow-content').eq(prev).removeClass('active');
    })                  
  }
  
  totop();
  jslogindestop();
jscalllogindestop();
  popup();
  cookiepopup();
  menumobile();
  jsBackLogin();
  mobilescroll();
  
  jsmenuiconh();
  menuhorizon();
  
  
  menudestopscroll1();
  
  
  
  menudestopscroll2();
  
  
   
  menudestopscroll3();
  
  
  
  menudestopscroll4();
  
  
  
  menudestopscroll5();
  
  
  
  menudestopscroll6();
  
  
  minicart();
  search();
  promo();
  slidehome5();
  slidehome4();
  slidehome3();
  slidehome1();
  slidehome2();
  jsBlogv3();
  brandv1();
  servicev1();
  servicev2();
  producth1banner();
  productnewh4();
  brandv2();
  Blogv1();
  Blogv2();

});