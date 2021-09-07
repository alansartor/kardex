<body class="book">
<div id="header">
<h1>Kardex Api</h1>
</div>
<div id="content">
<div id="preamble">
<div class="sectionbody">
<div class="paragraph">
<p>Documentacion de los metodos expuesto por la Api</p>
<p>(IDE: Eclipse - Import as Existing Maven Projects)</p>
<h2>Endpoints</h2>
<ul>
  <li><a href="#_post_login">POST /login</a></li>
  <li><a href="#_get_items">GET /items</a></li>
  <li><a href="#_post_entrada">POST /entrada</a></li>
  <li><a href="#_post_salida">POST /salida</a></li>
  <li>GET /stocks</li>
  <li>GET /stocks/{itemCodigo}</li>
</ul>
<table class="tableblock frame-all grid-all stretch">
<caption class="title">Datos de prueba:</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Username</th>
<th class="tableblock halign-left valign-top">Password</th>
<th class="tableblock halign-left valign-top">Rol</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>empleado</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>123456</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">EMPLEADO</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>cliente</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>123456</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">CLIENTE</p></td>
</tr>
</tbody>
</table>
</div>
</div>
</div>
<div class="sect1">
<h2 id="_post_login">POST /login</h2>
<div class="sectionbody">
<div class="paragraph">
<p>Devuleve token de seguridad para consumir los demas endpoints</p>
</div>
<div class="sect2">
<h3 id="_request">Request</h3>
<table class="tableblock frame-all grid-all stretch">
<caption class="title">Table 1. Request Body:</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>username</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Username</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>password</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Password</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_response">Response</h3>
<table class="tableblock frame-all grid-all stretch">
<caption class="title">Table 2. Response fields:</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>status</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Siempre 200</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>token</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Token de seguridad para consumir los demas metodos, expira dentre de 60 minutos</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_examples">Examples</h3>
<div class="listingblock">
<div class="title">Curl Request:</div>
<div class="content">
<pre class="highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/login' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json' \
    -d '{"username":"empleado","password":"123456"}'</code></pre>
</div>
</div>
<div class="listingblock">
<div class="title">HTTP Request:</div>
<div class="content">
<pre class="highlight nowrap"><code class="language-http" data-lang="http">GET /items HTTP/1.1
Authorization: Bearer Token...
Host: localhost:8080</code></pre>
</div>
</div>
<div class="listingblock">
<div class="title">HTTP Response:</div>
<div class="content">
<pre class="highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 209

{"status":200,"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlbXBsZWFkbyIsInJvbCI6IlJPTEVfRU1QTEVBRE8iLCJleHAiOjE2MzA5ODEwMTV9.cQeVkRNSAPRR85nRthHGy2oTg96CubOIUFW-mvHEk0LquN8-H0i3Q-HSHfjsIOd51ccyZ0hWnYcA3YNz6ph07w"}</code></pre>
</div>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="_post_entrada">POST /entrada</h2>
<div class="sectionbody">
<div class="paragraph">
<p>Registra ingreso de articulos al stock (solo puede ser ejecutado por usuario con rol EMPLEADO)</p>
</div>
<h3 id="_examples">Examples</h3>
<div class="listingblock">
<div class="title">Curl Request:</div>
<div class="content">
<pre class="highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/entrada' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json' \
	-H 'Authorization: Bearer Token...' \
    -d '{"itemCodigo": "CAM_0001","valorUnitario": 134 ,"cantidad": 18}'</code></pre>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="_post_salida">POST /salida</h2>
<div class="sectionbody">
<div class="paragraph">
<p>Registra salida de articulos del stock (solo puede ser ejecutado por usuario con rol CLIENTE)</p>
</div>
<h3 id="_examples">Examples</h3>
<div class="listingblock">
<div class="title">Curl Request:</div>
<div class="content">
<pre class="highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/salida' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -H 'Accept: application/json' \
	-H 'Authorization: Bearer Token...' \
    -d '{"itemCodigo": "CAM_0001","valorUnitario": 134 ,"cantidad": 18}'</code></pre>
</div>
</div>
</div>
</div>
<div class="sect1">
<h2 id="_get_items">GET /items</h2>
<div class="sectionbody">
<div class="paragraph">
<p>Devuelve todos los Articulos</p>
</div>
<div class="listingblock">
<div class="content">
<pre>Accesible para Roles:
 EMPLEADO
 CLIENTE</pre>
</div>
</div>
<div class="sect2">
<h3 id="_request_2">Request</h3>
<table class="tableblock frame-all grid-all stretch">
<caption class="title">Table 3. Request Header:</caption>
<colgroup>
<col style="width: 50%;">
<col style="width: 50%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Name</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Authorization</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Bearer Token&#8230;&#8203;</p></td>
</tr>
</tbody>
</table>
<div class="listingblock">
<div class="title">Request Body:</div>
<div class="content">
<pre class="highlight nowrap"><code></code></pre>
</div>
</div>
</div>
<div class="sect2">
<h3 id="_response_2">Response</h3>
<table class="tableblock frame-all grid-all stretch">
<caption class="title">Table 4. Response fields:</caption>
<colgroup>
<col style="width: 33.3333%;">
<col style="width: 33.3333%;">
<col style="width: 33.3334%;">
</colgroup>
<thead>
<tr>
<th class="tableblock halign-left valign-top">Path</th>
<th class="tableblock halign-left valign-top">Type</th>
<th class="tableblock halign-left valign-top">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>[].id</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>Number</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">El identificador unico del articulo</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>[].codigo</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">El codigo del articulo (es unico)</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>[].tipo</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Tipo de articulo</p></td>
</tr>
<tr>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>[].descripcion</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock"><code>String</code></p></td>
<td class="tableblock halign-left valign-top"><p class="tableblock">Descripcion del articulo</p></td>
</tr>
</tbody>
</table>
</div>
<div class="sect2">
<h3 id="_examples_2">Examples</h3>
<div class="listingblock">
<div class="title">Curl Request:</div>
<div class="content">
<pre class="highlight"><code class="language-bash" data-lang="bash">$ curl 'http://localhost:8080/items' -i -X GET \
    -H 'Authorization: Bearer Token...'</code></pre>
</div>
</div>
<div class="listingblock">
<div class="title">HTTP Request:</div>
<div class="content">
<pre class="highlight nowrap"><code class="language-http" data-lang="http">GET /items HTTP/1.1
Authorization: Bearer Token...
Host: localhost:8080</code></pre>
</div>
</div>
<div class="listingblock">
<div class="title">HTTP Response:</div>
<div class="content">
<pre class="highlight nowrap"><code class="language-http" data-lang="http">HTTP/1.1 200 OK
Content-Type: application/json
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 162

[{"id":1,"codigo":"CAM_0001","tipo":"Camiseta","descripcion":"Camiseta Marvel"},{"id":2,"codigo":"CAM_0002","tipo":"Camiseta","descripcion":"Camiseta DC comics"}]</code></pre>
</div>
</div>
</div>
</div>
</div>
</div>
<div id="footer">
<div id="footer-text">
Last updated 2021-09-06 22:14:50 -0300
</div>
</div>
</body>
