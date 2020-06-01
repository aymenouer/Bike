(function($) {
  function showPopup(selector) {
    $(selector).addClass('active');
  } window.showPopup=showPopup;

  function hidePopup(selector) {
    $(selector).removeClass('active');
  }

  function qtyProduct() {  
    $('.qtyplus').click(function(e){
      var fieldName = $(this).attr('data-field');
      var currentVal = parseInt($('input[name='+fieldName+']').val());

      if (!isNaN(currentVal)) {
        $('input[name='+fieldName+']').val(currentVal + 1);
      } else {
        $('input[name='+fieldName+']').val(1);
      }
      e.preventDefault();
    });

    $(".qtyminus").click(function(e) {

      var fieldName = $(this).attr('data-field');
      var currentVal = parseInt($('input[name='+fieldName+']').val());
      if (!isNaN(currentVal) && currentVal > 1) {
        $('input[name='+fieldName+']').val(currentVal - 1);    }
      else {
        $('input[name='+fieldName+']').val(1);
      }
      e.preventDefault();
    });
  }
  window.qtyProduct=qtyProduct;

  function doAjaxAddToCart(variant_id, quantity, title, image) {

    $.ajax({
      type: "post",
      url: "/cart/add.js",
      data: 'quantity=' + quantity + '&id=' + variant_id,
      dataType: 'json',
      beforeSend: function() {
        showPopup('.loading');
      },
      success: function(msg) {


        $('.tshopify-popup').removeClass('active');
        hidePopup('.quickview-product');
        Shopify.getCart(function(cart) {
          tbuildCart(cart);
        });
      },
      error: function(xhr, text) {
        hidePopup('.loading');
        $('.error-message').text($.parseJSON(xhr.responseText).description);
        showPopup('.error-popup');
      }
    });
  }window.doAjaxAddToCart=doAjaxAddToCart;

  function tbuildCart(cart) {
    // Start with a fresh cart div
    var $cartContainer = $('#CartContainer');
    $cartContainer.empty();

    // Show empty cart
    if (cart.item_count === 0) {
      $cartContainer
      .append('<p>' + "Your cart is currently empty." + '</p>');
      cartCallback(cart);
      return;
    }

    // Handlebars.js cart layout
    var items = [],
        item = {},
        data = {},
        source = $("#CartTemplate").html(),
        template = Handlebars.compile(source);

    // Add each item to our handlebars.js data
    $.each(cart.items, function(index, cartItem) {

      /* Hack to get product image thumbnail
       *   - If image is not null
       *     - Remove file extension, add _small, and re-add extension
       *     - Create server relative link
       *   - A hard-coded url of no-image
      */
      if (cartItem.image != null){
        var prodImg = cartItem.image.replace(/(\.[^.]*)$/, "_small$1").replace('http:', '');
      } else {
        var prodImg = "//cdn.shopify.com/s/assets/admin/no-image-medium-cc9732cb976dd349a0df1d39816fbcc7.gif";
      }

      // Create item's data object and add to 'items' array
      item = {
        id: cartItem.variant_id,
        line: index + 1, // Shopify uses a 1+ index in the API
        url: cartItem.url,
        img: prodImg,
        name: cartItem.product_title,
        variation: cartItem.variant_title,
        properties: cartItem.properties,
        itemAdd: cartItem.quantity + 1,
        itemMinus: cartItem.quantity - 1,
        itemQty: cartItem.quantity,
        price: Shopify.formatMoney(cartItem.price, ajaxCartConfig.moneyFormat),
        vendor: cartItem.vendor
      };

      items.push(item);
    });

    // Gather all cart data and add to DOM
    data = {
      items: items,
      note: cart.note,
      totalPrice: Shopify.formatMoney(cart.total_price, ajaxCartConfig.moneyFormat)
    }

    $cartContainer.append(template(data));
    $('#CartCount').html(cart.item_count);
    $('body').removeClass('drawer--is-loading');
    $('body').trigger('ajaxCart.afterCartLoad', cart);
  };

  function convertToSlug(text) {
    return text.toLowerCase().replace(/[^a-z0-9 -]/g, '').replace(/\s+/g, '-').replace(/-+/g, '-');
  } window.convertToSlug=convertToSlug;


  $(document).ready(function() {
    if ($(window).width() < 992 ) {
      responsiveQuickview();
    } else {
      quickView();
    }
    $(window).resize(function(){
      if ($(window).width() < 992 ) {
        responsiveQuickview();
      } else {
        quickView();
      }
    })

  });

  $(document).on('click','.overlay,.continue-shopping, .close-window', function() {
    hidePopup('.tshopify-popup');

    setTimeout(function(){
      $('.loading').removeClass('loaded-content');
    },500);
    return false;
  });


  function responsiveQuickview() {
    if ($(window).width() < 992 ) {
      $('.engoj_btn_quickview').each(function(){
        var linkProduct='/products/' + $(this).attr('data-id');
        $(this).attr('href',linkProduct);    
      })
    } else {
      $('.engoj_btn_quickview').attr('href','javascript:void(0)');
    }
    $(window).resize(function(){
      if ($(window).width() < 992 ) {
        $('.engoj_btn_quickview').each(function(){
          var linkProduct='/products/' + $(this).attr('data-id');
          $(this).attr('href',linkProduct);    
        })
      } else {
        $('.engoj_btn_quickview').attr('href','javascript:void(0)');
      }
    })
  }

  function quickView() {
    $('.engoj_btn_quickview').click(function() {
      showPopup('.loading');
      var id = $(this).data('id');
      var rating = (($(this).closest('.product-info').find('.spr-badge').attr('data-rating')) * 20)+"%";

      Shopify.getProduct(id, function(product) {
        var template = $('#quick-view').html();
        $('.quickview-product').html(template);
        var quickview = $('.quickview-product');
        quickview.find('.product-name a').html(product.title).attr('href', product.url);
        quickview.find('.spr-badge .spr-active').css({"width": rating});
        quickview.find('.star-rating .shopify-product-reviews-badge').attr('data-id',product.id)
        if (quickview.find('.des').length > 0) {
          var description = product.description.replace(/(<([^>]+)>)/ig, "");
          description = description.split(" ").splice(0, 20).join(" ") + "...";
          quickview.find('.des').text(description);
        } else {
          quickview.find('.des').remove();
        }

        quickview.find('.price').html(Shopify.formatMoney(product.price, window.money_format));
        quickview.find('.product-inner').attr('id', 'product-' + product.id);
        quickview.find('.variants').attr('id', 'product-actions-' + product.id);
        quickview.find('.variants select').attr('id', 'product-select-' + product.id);

        if (product.compare_at_price > product.price) {
          quickview.find('.compare-price').html(Shopify.formatMoney(currencyConverter(product.compare_at_price_max), window.money_format)).show();
          quickview.find('.price').addClass('on-sale');
        } else {
          quickview.find('.compare-price').html('');
          quickview.find('.price').removeClass('on-sale');
        }

        //out of stock
        if (!product.available) {
          quickview.find("select, input, .total-price, .dec, .inc, .variants label").remove();
          quickview.find(".btn-addToCart").text('unavailable').addClass('disabled').attr("disabled", "disabled");;
        } else {
          quickview.find('.total-price span').html(Shopify.formatMoney(currencyConverter(product.price), window.money_format));

          
          tshopifyQuickview.createQuickViewVariantsSwatch(product, quickview);
          
        }

        qtyProduct();
        tshopifyQuickview.quickViewSlider(product, quickview);
        tshopifyQuickview.initQuickviewAddToCart();


        $('.quickview-product').addClass('active');
        $('.loading').addClass('loaded-content');

        if ($('.quickview-product .total-price').length > 0) {
          $('.quickview-product span[class=qtyplus]').on('click', tshopifyQuickview.updatePricingQuickview);
          $('.quickview-product span[class=qtyminus]').on('click', tshopifyQuickview.updatePricingQuickview);
        }

      });
      return false;
    });
  } window.quickView=quickView;

  var tshopifyQuickview = {

    selectCallbackQuickview: function(variant, selector) {
      var self = this;
      var productItem = jQuery('.quickview-product .product-item'),
          addToCart = productItem.find('.btn-addToCart'),
          productPrice = productItem.find('.price'),
          comparePrice = productItem.find('.compare-price'),
          totalPrice = productItem.find('.total-price span');

      if (variant && variant.featured_image) {
        var originalImage = jQuery(".engoj_img_main_quickview");
        var newImage = variant.featured_image;
        var element = originalImage[0];
        Shopify.Image.switchImage(newImage, element, function (newImageSizedSrc, newImage, element) {
          var $el = $(element);
          $el.attr('src', newImageSizedSrc);
        });        
      }

      if (variant) {
        if (variant.available) {
          addToCart.removeClass('disabled').removeAttr('disabled').text('Add to Cart');
                                                                        } else {
                                                                        
                                                                        addToCart.val('sold_out').addClass('disabled').attr('disabled', 'disabled').text('Sold Out');
        }
        productPrice.html(Shopify.formatMoney(currencyConverter(variant.price), window.money_format));



          if ( variant.compare_at_price > variant.price ) {
            comparePrice.html(Shopify.formatMoney(currencyConverter(variant.compare_at_price), window.money_format)).show();
            productPrice.addClass('on-sale');
          } else {
            comparePrice.hide();
            productPrice.removeClass('on-sale');
          }

          var form = jQuery('#' + selector.domIdPrefix).closest('form');
          for (var i=0,length=variant.options.length; i<length; i++) {
            var radioButton = form.find('.swatch[data-option-index="' + i + '"] :radio[value="' + variant.options[i] +'"]');
            if (radioButton.size()) {
              radioButton.get(0).checked = true;
            }
          }


          var inventoryInfo = productItem.find('.product-inventory span');
          if (variant.available) {
            if (variant.inventory_management!=null) {
              inventoryInfo.text(variant.inventory_quantity + " " + 'in_stock');
            } else {
              inventoryInfo.text('many_in_stock');
            }
          } else {
            inventoryInfo.text('out_of_stock');
          }

          // Total:
          var regex = /([0-9]+[.|,][0-9]+[.|,][0-9]+)/g;
          var unitPriceTextMatch = $('.quickview-product .price').text().match(regex);

          if (!unitPriceTextMatch) {
            regex = /([0-9]+[.|,][0-9]+)/g;
            unitPriceTextMatch = $('.quickview-product .price').text().match(regex);
          }

          if (unitPriceTextMatch) {
            var unitPriceText = unitPriceTextMatch[0];
            var unitPrice = unitPriceText.replace(/[.|,]/g, '');
            var quantity = parseInt($('.quickview-product input[name=quantity]').val());
            var totalPrice = unitPrice * quantity;
            totalPrice = currencyConverter(totalPrice);

            var totalPriceText = Shopify.formatMoney(totalPrice, window.money_format);
            regex = /([0-9]+[.|,][0-9]+[.|,][0-9]+)/g;
            if (!totalPriceText.match(regex)) {
              regex = /([0-9]+[.|,][0-9]+)/g;
            }
            totalPriceText = totalPriceText.match(regex)[0];

            var regInput = new RegExp(unitPriceText, "g");
            var totalPriceHtml = $('.quickview-product .price').html().replace(regInput, totalPriceText);

            $('.quickview-product .total-price span').html(totalPriceHtml);
          }

          if (variant && variant.featured_image) {
            var newImage = Shopify.resizeImage(variant.featured_image.src, 'small');
            newImage = newImage.replace(/https?:/,'');
            jQuery('.quick-view .quickview-more-views img').each(function() {
              var grandSize = jQuery(this).attr('src');
              if (grandSize == newImage) {
                jQuery(this).parent().trigger('click');
                return false;
              }
            });
          }

        } else {
          addToCart.text('unavailable').addClass('disabled').attr('disabled', 'disabled');
        }

        Currency.convertAll(shopCurrency, jQuery('#currencies a.selected').attr('data-currency'));
        jQuery('.selected-currency').text(Currency.currentCurrency);

      },
        /* Quick View SWATCH */
        createQuickViewVariantsSwatch: function(product, quickviewTemplate) {
          var self = this;
          if (product.variants.length > 1) {
            for (var i = 0; i < product.variants.length; i++) {
              var variant = product.variants[i];
              var option = '<option value="' + variant.id + '">' + variant.title + '</option>';
              quickviewTemplate.find('form.variants > select').append(option);
            }

            new Shopify.OptionSelectors("product-select-" + product.id, {
              product: product,
              onVariantSelected: self.selectCallbackQuickview,
              enableHistoryState: true
            });

            var filePath = window.file_url.substring(0, window.file_url.lastIndexOf('?'));
            var assetUrl = window.asset_url.substring(0, window.asset_url.lastIndexOf('?'));
            var options = "";

            for (var i = 0; i < product.options.length; i++) {
              options += '<div class="swatch clearfix" data-option-index="' + i + '">';
              options += '<div class="header">' + product.options[i].name + '</div>';
              var is_color = false;
              if (/Color|Colour/i.test(product.options[i].name)) {
                is_color = true;
              }
              var optionValues = new Array();
              for (var j = 0; j < product.variants.length; j++) {
                var variant = product.variants[j];
                var value = variant.options[i];
                var valueHandle = convertToSlug(value);
                var forText = 'swatch-' + i + '_' + valueHandle;
                if (optionValues.indexOf(value) < 0) {
                  //not yet inserted
                  options += '<div data-value="' + value + '" class="swatch-element ' + (is_color ? "color" : "") + valueHandle + (variant.available ? ' available ' : ' soldout ') + '">';

                  
                  options += '<input id="' + forText + '" type="radio" name="option-' + i + '" value="' + value + '" ' + (j == 0 ? ' checked ' : '') + (variant.available ? '' : ' disabled') + ' />';

                  if (is_color) {
                    options += '<label class="color-quickview" for="' + forText + '" style="background-color: ' + valueHandle + ';"></label>';
                  } else {
                    options += '<label class="size-quickview" for="' + forText + '">' + value + '</label>';
                  }
                  options += '</div>';
                  if (variant.available) {
                    $('.quickview-product .swatch[data-option-index="' + i + '"] .' + valueHandle).removeClass('soldout').addClass('available').find(':radio').removeAttr('disabled');
                  }
                  optionValues.push(value);
                }
              }
              options += '</div>';
            }
            quickviewTemplate.find('form.variants > select').after(options);
            quickviewTemplate.find('.swatch :radio').change(function() {
              var optionIndex = $(this).closest('.swatch').attr('data-option-index');
              var optionValue = $(this).val();
              $(this)
              .closest('form')
              .find('.single-option-selector')
              .eq(optionIndex)
              .val(optionValue)
              .trigger('change');
            });

            if (product.available && product.options.size > 1) {
              Shopify.optionsMap = {};
              Shopify.linkOptionSelectors(product);
            }

          } else {
            quickviewTemplate.find('form.variants > select').remove();
            var variant_field = '<input type="hidden" name="id" value="' + product.variants[0].id + '">';
            quickviewTemplate.find('form.variants').append(variant_field);
          }
        },

          /* Quick View */
          createQuickViewVariants: function(product, quickviewTemplate) {
            var self = this;
            if (product.variants.length > 1) {
              for (var i = 0; i < product.variants.length; i++) {
                var variant = product.variants[i];
                var option = '<option value="' + variant.id + '">' + variant.title + '</option>';
                quickviewTemplate.find('form.variants > select').append(option);
              }

              new Shopify.OptionSelectors("product-select-" + product.id, {
                product: product,
                onVariantSelected: self.selectCallbackQuickview,
                enableHistoryState: true
              });

              //$('.quickview-product .single-option-selector').selectize();
              $('.quickview-product .selectize-input input').attr("disabled", "disabled");

              if (product.options.length == 1) {
                $('.selector-wrapper:eq(0)').prepend('<label>' + product.options[0].name + '</label>');
              }

              quickviewTemplate.find('form.variants .selector-wrapper label').each(function(i, v) {
                $(this).html(product.options[i].name);
              });

            } else {
              quickviewTemplate.find('form.variants > select').remove();
              var variant_field = '<input type="hidden" name="id" value="' + product.variants[0].id + '">';
              quickviewTemplate.find('form.variants').append(variant_field);
            }

          },

            /* Quick View VIEWMORE Slider */
            quickViewSlider: function(product, quickviewTemplate) {
              var featuredImage = Shopify.resizeImage(product.featured_image, 'grande');
              quickviewTemplate.find('.featured-image').append('<a title="'+ product.title +'" class="product-photo" href="' + product.url + '"><img class="engoj_img_main_quickview" src="' + featuredImage + '" alt="' + product.title + '"/><span class="loading" style="height: 100%; width: 100%; top:0; left:0 z-index: 999; position: absolute; display: none; background: url(' + window.loading_url + ') center no-repeat;"></span></a>');
              if (product.images.length != 0) {
                var quickViewCarousel = quickviewTemplate.find('.more-views .owl-carousel');

                for (i in product.images) {
                  var grande = Shopify.resizeImage(product.images[i], 'grande');
                  var compact = Shopify.resizeImage(product.images[i], 'compact');
                  var item = '<div class="item"><a href="javascript:void(0)" data-image="' + grande + '"><img src="' + compact + '"  /></a></div>';
                  quickViewCarousel.append(item);
                }

                quickViewCarousel.find('a').click(function() {
                  var featureImage = quickviewTemplate.find('.featured-image img');
                  var moreviewLoad = quickviewTemplate.find('.featured-image .loading');
                  if (featureImage.attr('src') != $(this).attr('data-image')) {
                    featureImage.attr('src', $(this).attr('data-image'));
                    moreviewLoad.show();
                    featureImage.load(function(e) {
                      moreviewLoad.hide();
                      $(this).unbind('load');
                      moreviewLoad.hide();
                    });
                  }
                });

              } else {
                quickviewTemplate.find('.more-views').remove();
              }

            },

              /* Quick View ADD TO CART */
              initQuickviewAddToCart: function() {
                if ($('.quickview-product .btn-addToCart').length > 0) {
                  $('.quickview-product .btn-addToCart').click(function() {
                    var variant_id = $('.quickview-product select[name=id]').val();
                    if (!variant_id) {
                      variant_id = $('.quickview-product input[name=id]').val();
                    }
                    var quantity = $('.quickview-product input[name=quantity]').val();
                    if (!quantity) {
                      quantity = 1;
                    }

                    var title = $('.quickview-product .product-name a').html();
                    var image = $('.quickview-product .featured-image img').attr('src');

                    doAjaxAddToCart(variant_id, quantity, title, image);

                  });
                }
              },
                /* Quick View update Pricing */
                updatePricingQuickview: function() {
                  var regex = /([0-9]+[.|,][0-9]+[.|,][0-9]+)/g;
                  var unitPriceTextMatch = $('.quickview-product .price').text().match(regex);

                  if (!unitPriceTextMatch) {
                    regex = /([0-9]+[.|,][0-9]+)/g;
                    unitPriceTextMatch = $('.quickview-product .price').text().match(regex);
                  }

                  if (unitPriceTextMatch) {
                    var unitPriceText = unitPriceTextMatch[0];
                    var unitPrice = unitPriceText.replace(/[.|,]/g, '');
                    var quantity = parseInt($('.quickview-product input[name=quantity]').val());
                    var totalPrice = unitPrice * quantity;

                    var totalPriceText = Shopify.formatMoney(totalPrice, window.money_format);
                    regex = /([0-9]+[.|,][0-9]+[.|,][0-9]+)/g;
                    if (!totalPriceText.match(regex)) {
                      regex = /([0-9]+[.|,][0-9]+)/g;
                    }
                    totalPriceText = totalPriceText.match(regex)[0];

                    var regInput = new RegExp(unitPriceText, "g");
                    var totalPriceHtml = $('.quickview-product .price').html().replace(regInput, totalPriceText);

                    $('.quickview-product .total-price span').html(totalPriceHtml);
                  }
                }

    }
  })(jQuery);