
<%@ include file="../../layouts/header.jsp" %>
<!-- C3 charts css -->
<link href="public/plugins/c3/c3.min.css" rel="stylesheet" type="text/css"/>
<!-- DataTables -->
<link href="public/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<link href="public/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<!-- Responsive datatable examples -->
<link href="public/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<link href="public/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css"
      rel="stylesheet">
<%@ include file="../../layouts/headerStyle.jsp" %>
<body class="fixed-left">
<%@ include file="../../layouts/loader.jsp" %>
<!-- Begin page -->
<div id="wrapper">
  <%@ include file="../../layouts/navbar.jsp" %>
  <!-- Start right Content here -->
  <div class="content-page">
    <!-- Start content -->
    <div class="content">
      <!-- Top Bar Start -->
      <%@ include file="../../layouts/toolbar.jsp" %>
      <!-- Top Bar End -->
      <!-- ==================
      PAGE CONTENT START
      ================== -->
      <div class="page-content-wrapper">
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-6 col-xl-6">
              <div class="mini-stat clearfix bg-white">
 <span class="mini-stat-icon bg-blue-grey mr-0 float-right"><i

         class="mdi mdi-black-mesa"></i></span>
                <div class="mini-stat-info">
                  <span id="autores_registrados" class="counter text-blue-grey">0</span>
                  Autores registrados
                </div>
                <div class="clearfix"></div>
              </div>
            </div>
            <div class="col-md-6 col-xl-6" id="registrar_autor" style="cursor: pointer;">
              <div class="mini-stat clearfix bg-white">
 <span class="mini-stat-icon bg-teal mr-0 float-right"><i
         class="mdi mdi-account"></i></span>
                <div class="mini-stat-info">
                  <span class="counter text-teal">Registrar</span>
                  Autor
                </div>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-12">
              <div class="card m-b-20">
                <div class="card-body">
                  <div id="tbAutores"></div>
                </div>
              </div>
            </div>
          </div>
        </div><!-- container -->
      </div> <!-- Page content Wrapper -->
    </div> <!-- content -->
    <%@ include file="../../layouts/footer.jsp" %>
  </div>
  <!-- End Right content here -->

  <div class="modal fade" id="mdRegisAutor" tabindex="-1" role="dialog" arialabelledby="exampleModalLabel"
       aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Nuevo | Autor<br>
            <sub>Campos marcados con * son obligatorios</sub>
          </h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form name="formulario_registro" id="formulario_registro">
            <input type="hidden" id="consultar_datos" name="consultar_datos"
                   value="insertarAutores">
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label>Código *</label>
                  <input type="text" autocomplete="off" name="codigoAutor"
                         data-parsley-error-message="Campo requerido" id="codigoAutor"
                         class="form-control"
                         required placeholder="Ingrese código del Autor"/>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label>Nombre *</label>
                  <input type="text" data-parsley-error-message="Campo requerido"
                         autocomplete="off"
                         name="nombreAutor" id="nombreAutor" class="form-control"
                         required
                         placeholder="Ingrese nombre del Autor"/>
                </div>
              </div>
            </div>
              <div class="row">
                  <div class="col-md-6">
                      <div class="form-group">
                          <label>Apellido *</label>
                          <input type="text" autocomplete="off" name="apellidoAutor"
                                 data-parsley-error-message="Campo requerido" id="apellidoAutor"
                                 class="form-control"
                                 required placeholder="Ingrese apellido del Autor"/>
                      </div>
                  </div>
                  <div class="col-md-6">
                      <div class="form-group">
                          <label>Fecha de Nacimiento *</label>
                          <input type="date" autocomplete="off" name="fechaNacimiento"
                                 data-parsley-error-message="Campo requerido" id="fechaNacimiento"
                                 class="form-control" required/>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <div class="col-md-6">
                      <div class="form-group">
                          <label>Nacionalidad *</label>
                          <select class="form-control" id="nacionalidadAutor" name="nacionalidadAutor" >
                              <option value="Seleccione" selected>Seleccione</option>
                              <option value="Nacional" >Nacional</option>
                              <option value="Extranjero" >Extranjero</option>
                          </select>
                      </div>
                  </div>
              </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" datadismiss="modal">Cerrar</button>
              <button type="submit" class="btn btn-primary">Guardar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

  <!-- END wrapper -->
  <%@ include file="../../layouts/footerScript.jsp" %>
  <!-- Peity chart JS -->
  <script src="public/plugins/peity-chart/jquery.peity.min.js"></script>
  <!--C3 Chart-->
  <script src="public/plugins/d3/d3.min.js"></script>
  <script src="public/plugins/c3/c3.min.js"></script>
  <!-- KNOB JS -->
  <script src="public/plugins/jquery-knob/excanvas.js"></script>
  <script src="public/plugins/jquery-knob/jquery.knob.js"></script>
  <!-- Page specific js -->
  <script src="public/assets/pages/dashboard.js"></script>
  <!-- App js -->
  <script src="public/assets/js/app.js"></script>
  <script src="public/plugins/datatables/jquery.dataTables.min.js"></script>
  <script src="public/plugins/datatables/dataTables.bootstrap4.min.js"></script>
  <script type="text/javascript" src="public/plugins/parsleyjs/parsley.min.js"></script>
  <script src="public/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <%@page import="java.time.LocalDateTime" %>
  <%@ page import="java.time.temporal.ChronoField" %>
  <script src="./modulos/autores/autores_funciones.js"></script>
