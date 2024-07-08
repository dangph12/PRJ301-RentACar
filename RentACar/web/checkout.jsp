<%-- 
    Document   : checkout
    Created on : Jul 8, 2024, 4:10:54 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html
    xmlns="http://www.w3.org/1999/xhtml"
    xml:lang="en"
    lang="en"
    class="flexbox"
    >
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>
            </title>
            <link href="css/check_out.css" rel="stylesheet" type="text/css" media="all" />
        </head>
        <body style="">
            <form id="real-form" action="finish-orders" method="POST">
                <div class="content">
                    <div class="wrap">
                        <div class="sidebar">
                            <div class="sidebar-content">
                                <div class="order-summary order-summary-is-collapsed">
                                    <h2 class="visually-hidden">Thông tin đơn hàng</h2>
                                    <div class="order-summary-sections">
                                        <div
                                            class="order-summary-section order-summary-section-product-list"
                                            data-order-summary-section="line-items"
                                            >
                                            <table class="product-table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">
                                                            <span class="visually-hidden">Hình ảnh</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span class="visually-hidden">Mô tả</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span class="visually-hidden">Số lượng</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span class="visually-hidden">Giá</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr
                                                        class="product"
                                                        >
                                                        <td class="product-image">
                                                            <div class="product-thumbnail">
                                                                <div class="product-thumbnail-wrapper">
                                                                    <img
                                                                        class="product-thumbnail-image"
                                                                        alt="${requestScope.selectedCategory.title}"
                                                                        src="${requestScope.selectedCategory.image}"
                                                                        />
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="product-description">
                                                            <span><input type="hidden" name="selected-category-uid" value="${requestScope.selectedCategory.categoryUID}"></span>
                                                            <span>${requestScope.selectedCategory.title}</span>
                                                        </td>
                                                        <td class="product-quantity">
                                                            <span>Số lượng</span> <input type="number" name="car-count" min="1" max="30" value="1"/>
                                                        </td>
                                                        <td class="product-price">
                                                            <span class="order-summary-emphasis">${requestScope.selectedCategory.unitPrice}₫</span>
                                                            <input type="hidden" name="total-amount" value="" />
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div
                                            class="order-summary-section order-summary-section-total-lines payment-lines"
                                            data-order-summary-section="payment-lines"
                                            >
                                            <table class="total-line-table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">
                                                            <span class="visually-hidden">Mô tả</span>
                                                        </th>
                                                        <th scope="col">
                                                            <span class="visually-hidden">Giá</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tfoot class="total-line-table-footer">
                                                    <tr class="total-line">
                                                        <td class="total-line-name payment-due-label">
                                                            <span class="payment-due-label-total">Tổng cộng</span>
                                                        </td>
                                                        <td class="total-line-name payment-due">
                                                            <span class="payment-due-currency">VND</span>
                                                            <span
                                                                class="payment-due-price"
                                                                >
                                                                66,000₫
                                                            </span>
                                                            <span
                                                                class="checkout_version"
                                                                display:none=""
                                                                data_checkout_version="15"
                                                                >
                                                            </span>
                                                        </td>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="main">
                            <div class="main-content">
                                <div class="section">
                                    <div class="section-header">
                                        <h2 class="section-title">Thông tin giao hàng</h2>
                                    </div>
                                    <div class="section-content section-customer-information">
                                        <div class="fieldset">
                                            <div class="field field-required">
                                                <div class="field-input-wrapper">
                                                    <label
                                                        class="field-label"
                                                        for="fullname"
                                                        >Họ và tên</label
                                                    >
                                                    <input
                                                        placeholder="Họ và tên"
                                                        autocapitalize="off"
                                                        spellcheck="false"
                                                        class="field-input"
                                                        size="30"
                                                        type="text"
                                                        id="fullname"
                                                        name="fullname"
                                                        value=""
                                                        autocomplete="false"
                                                        />
                                                </div>
                                            </div>

                                            <div class="field field-required">
                                                <div class="field-input-wrapper">
                                                    <label
                                                        class="field-label"
                                                        for="address"
                                                        >Địa chỉ nhận xe</label
                                                    >
                                                    <input
                                                        placeholder="Địa chỉ nhận xe"
                                                        autocapitalize="off"
                                                        spellcheck="false"
                                                        class="field-input"
                                                        size="30"
                                                        type="text"
                                                        id="address"
                                                        name="address"
                                                        value=""
                                                        autocomplete="false"
                                                        />
                                                </div>
                                            </div>
                                            <div class="field field-required field-two-thirds">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="email"
                                                           >Email</label
                                                    >
                                                    <input
                                                        autocomplete="false"
                                                        placeholder="Email"
                                                        autocapitalize="off"
                                                        spellcheck="false"
                                                        class="field-input"
                                                        size="30"
                                                        type="email"
                                                        id="email"
                                                        name="email"
                                                        value=""
                                                        />
                                                </div>
                                            </div>
                                            <div class="field field-required field-third">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="phone"
                                                           >Số điện thoại</label
                                                    >
                                                    <input
                                                        autocomplete="false"
                                                        placeholder="Số điện thoại"
                                                        autocapitalize="off"
                                                        spellcheck="false"
                                                        class="field-input"
                                                        size="30"
                                                        maxlength="15"
                                                        type="tel"
                                                        id="phone"
                                                        name="phone"
                                                        value=""
                                                        />
                                                </div>
                                            </div>
                                            <div class="field field-required field-two-thirds">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="received-at"
                                                           >Ngày nhận xe</label
                                                    >
                                                    <div class="field">Ngày nhận xe: <input id="received-at" type="date" name="received-at" value="" placeholder="Ngày nhận xe"/></div>
                                                </div>
                                            </div>
                                            <div class="field field-required field-third">
                                                <div class="field-input-wrapper">
                                                    <label class="field-label" for="rental-days"
                                                           >Số điện thoại</label
                                                    >
                                                    <div class="field">Số ngày thuê xe: <input id="rental-days" type="number" name="rental-days" min="1" max="30" value="1" /></div>     
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="section-content">
                                        <div class="fieldset"></div>
                                    </div>
                                    <div id="change_pick_location_or_shipping">
                                        <div id="section-payment-method" class="section">
                                            <div class="order-checkout__loading--box">
                                                <div class="order-checkout__loading--circle"></div>
                                            </div>
                                            <div class="section-header">
                                                <h2 class="section-title">Phương thức thanh toán</h2>
                                            </div>
                                            <div class="section-content">
                                                <div class="content-box">
                                                    <div class="radio-wrapper content-box-row">
                                                        <label
                                                            class="radio-label"
                                                            for="payment_method_id"
                                                            >
                                                            <div class="radio-input payment-method-checkbox">
                                                                <input
                                                                    id="payment_method_id"
                                                                    class="input-radio"
                                                                    name="payment-method"
                                                                    type="radio"
                                                                    value="1"
                                                                    checked="checked"
                                                                    />
                                                            </div>
                                                            <div class="radio-content-input">
                                                                <img
                                                                    class="main-img"
                                                                    src="assets/cod.svg"
                                                                    />
                                                                <div>
                                                                    <span class="radio-label-primary"
                                                                          >Thanh toán khi giao hàng (COD)</span
                                                                    >
                                                                    <span class="quick-tagline hidden"></span>
                                                                </div>
                                                            </div>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="step-footer">
                                <input class="step-footer-continue-btn btn" form="real-form" type="submit" value="Hoàn tất đơn hàng">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <script>
                setInterval(function () {
                    var rentalDays = parseInt(document.getElementsByName("rental-days")[0].value);
                    var carCount = parseInt(document.getElementsByName("car-count")[0].value);
                    var productPrice = parseFloat(document.getElementsByClassName("product-price")[0].innerText.replace(",", ""));
                    var paymentDuePrice = rentalDays * carCount * productPrice;
                    document.getElementsByName("total-amount")[0].value = paymentDuePrice.toFixed(0);
                    document.getElementsByClassName("payment-due-price")[0].innerText = paymentDuePrice.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ".") + "₫";
                }, 100);
            </script>
        </body>
    </html>
