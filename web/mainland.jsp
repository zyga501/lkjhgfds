<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" pageEncoding="utf-8" %>
<div class="container">
  <div class="row clearfix">
    <div class="col-md-6 column">
      <div class="carousel slide" id="carousel-218670">
        <ol class="carousel-indicators">
          <li data-slide-to="0" data-target="#carousel-218670">
          </li>
          <li data-slide-to="1" data-target="#carousel-218670" class="active">
          </li>
          <li data-slide-to="2" data-target="#carousel-218670">
          </li>
        </ol>
        <div class="carousel-inner">
          <s:iterator id="map" value="#request.navpageList">
            <div class="item">  <img alt="" src='<s:property value="#map.href"/>'/>
              <div class="carousel-caption">
                <p>
                  <s:property value="#map.label"/>
                </p>
              </div>
            </div>
        </s:iterator>
        </div> <a class="left carousel-control" href="#carousel-218670" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-218670" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
      </div>
    </div>
    <div class="col-md-6 column">
      <div class="alert alert-dismissable alert-success">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <strong>Warning!</strong> Best check yo self, you're not looking too good. <a href="#" class="alert-link">alert link</a>
      </div>
      <dl>
        <dt>
          Description lists
        </dt>
        <dd>
          A description list is perfect for defining terms.
        </dd>
        <dt>
          Euismod
        </dt>
        <dd>
          Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.
        </dd>
        <dd>
          Donec id elit non mi porta gravida at eget metus.
        </dd>
        <dt>
          Malesuada porta
        </dt>
        <dd>
          Etiam porta sem malesuada magna mollis euismod.
        </dd>
        <dt>
          Felis euismod semper eget lacinia
        </dt>
        <dd>
          Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
        </dd>
      </dl>
    </div>
  </div>
  <div class="row clearfix">
    <div class="col-md-4 column">
      <ul class="nav nav-pills">
        <li class="active">
          <a href="#"> <span class="badge pull-right">42</span> Home</a>
        </li>
        <li>
          <a href="#"> <span class="badge pull-right">16</span> More</a>
        </li>
      </ul>
      <div class="list-group">
        <a href="#" class="list-group-item active">Home</a>
        <div class="list-group-item">
          List header
        </div>
        <div class="list-group-item">
          <h4 class="list-group-item-heading">
            List group item heading
          </h4>
          <p class="list-group-item-text">
            ...
          </p>
        </div>
        <div class="list-group-item">
          <span class="badge">14</span> Help
        </div> <a class="list-group-item active"> <span class="badge">14</span> Help</a>
      </div>
    </div>
    <div class="col-md-4 column">
      <ul>
        <li>
          Lorem ipsum dolor sit amet
        </li>
        <li>
          Consectetur adipiscing elit
        </li>
        <li>
          Integer molestie lorem at massa
        </li>
        <li>
          Facilisis in pretium nisl aliquet
        </li>
        <li>
          Nulla volutpat aliquam velit
        </li>
        <li>
          Faucibus porta lacus fringilla vel
        </li>
        <li>
          Aenean sit amet erat nunc
        </li>
        <li>
          Eget porttitor lorem
        </li>
      </ul>
    </div>
    <div class="col-md-4 column">
      <h2>
        Heading
      </h2>
      <p>
        Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
      </p>
      <p>
        <a class="btn" href="#">View details »</a>
      </p>
    </div>
  </div>
</div>