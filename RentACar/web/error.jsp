<%-- 
    Document   : 404
    Created on : Jul 15, 2024, 3:08:08 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lỗi</title>
    <style>
      * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
      }

      body {
        font-family: "Helvetica Neue", arial, "Hiragino Kaku Gothic ProN",
          Meiryo, sans-serif;
        font-size: 14px;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        -webkit-font-kerning: normal;
        font-kerning: normal;
        color: #333;
        background-color: #f3f5f8;
      }

      .wrapper {
        display: -ms-grid;
        display: grid;
        grid-gap: 16px;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        padding: 40px 0;
        text-align: center;
        color: #333;
        overflow: hidden;
      }
      @media (max-width: 768px) {
        .wrapper {
          padding: 24px 0;
        }
      }

      .logo {
        -ms-grid-column-align: center;
        justify-self: center;
        display: block;
        width: 148px;
      }

      .illust {
        position: relative;
        width: 508px;
        overflow: hidden;
      }
      .illust--default {
        border-radius: 36px;
      }
      .illust::before {
        content: "";
        display: block;
        padding-top: 70.86%;
      }
      .illust--default::before {
        padding-top: 50%;
      }
      .illust img {
        display: block;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
      }
      @media (max-width: 768px) {
        .illust {
          width: 452px;
        }
        .illust--default {
          width: 100vw;
          border-radius: 0;
        }
      }

      .text-wrapper {
        display: -ms-grid;
        display: grid;
        grid-gap: 4px;
        margin-top: -8px;
      }
      .text-wrapper h2 {
        font-size: 32px;
        font-weight: bold;
        line-height: 48px;
      }
      .text-wrapper p {
        font-size: 14px;
        line-height: 22px;
      }

      .link-button {
        -ms-grid-column-align: center;
        justify-self: center;
        display: -ms-grid;
        display: grid;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        font-weight: bold;
        color: #fff;
        text-decoration: none;
        height: 40px;
        padding: 0 40px;
        border-radius: 20px;
        background-color: #ffbb28;
        margin-top: 8px;
        transition: background-color 0.1s ease-out;
      }
      .link-button:hover {
        background-color: #ffa000;
      }
    </style>
    <style id="__web-inspector-hide-shortcut-style__">
      .__web-inspector-hide-shortcut__,
      .__web-inspector-hide-shortcut__ *,
      .__web-inspector-hidebefore-shortcut__::before,
      .__web-inspector-hideafter-shortcut__::after {
        visibility: hidden !important;
      }
    </style>
  </head>
  <body>
    <div class="wrapper">
      <div class="illust">
        <img src="./assets/404.png" />
      </div>
      <div class="text-wrapper">
        <h2>Lỗi</h2>
        <p><c:out value="${error}">Trang không tồn tại</c:out></p>
      </div>
      <a class="link-button" href="/RentACar/${backPage}">Quay trở lại</a>
    </div>
  </body>
</html>

