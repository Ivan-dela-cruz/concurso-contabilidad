-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-01-2020 a las 08:26:48
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistema_encuestas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificaciones`
--

CREATE TABLE `calificaciones` (
  `id` int(11) NOT NULL,
  `id_encuesta` int(11) NOT NULL,
  `id_usuario` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `calificacion` double DEFAULT NULL,
  `aciertos` int(11) DEFAULT NULL,
  `numero_preguntas` int(11) DEFAULT NULL,
  `tiempo` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `calificaciones`
--

INSERT INTO `calificaciones` (`id`, `id_encuesta`, `id_usuario`, `calificacion`, `aciertos`, `numero_preguntas`, `tiempo`) VALUES
(5, 15, 'dfdf', 0, 0, 5, '00:02:00'),
(6, 15, '1752544', 2, 1, 5, '00:14:00'),
(7, 15, '454', 2, 1, 5, '00:10:02'),
(8, 14, 'GHG', 4, 2, 5, '00:20:56');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encuestas`
--

CREATE TABLE `encuestas` (
  `id` int(11) NOT NULL,
  `codigo` varchar(120) COLLATE utf8mb4_spanish_ci NOT NULL,
  `categoria` varchar(220) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `nivel` varchar(120) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `titulo` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `estado` int(1) NOT NULL,
  `tiempo` int(11) DEFAULT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_final` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `encuestas`
--

INSERT INTO `encuestas` (`id`, `codigo`, `categoria`, `nivel`, `titulo`, `descripcion`, `estado`, `tiempo`, `fecha_inicio`, `fecha_final`) VALUES
(14, '001', 'COSTOS', 'BASICO', 'TEST DE CONTABILIDAD DE COSTES-EXAMEN.- ', 'ELEJIR UA SOLA RESPUESTA PARA CADA PREGUNTA, EL CUESTIONARIO SE CERRARA CULMINADO EL TIEMPO, DESIGNADO.', 1, 1, '2019-12-30', '2019-12-30'),
(15, '003', 'AUDITORIA', 'MEDIO', 'TEST DE CONTABILIDAD DE AUDITORIA-EXAMEN.- ', 'ELEJIR UA SOLA RESPUESTA PARA CADA PREGUNTA, EL CUESTIONARIO SE CERRARA CULMINADO EL TIEMPO, DESIGNADO.', 1, 1, '2019-12-30', '2019-12-30'),
(16, '002', 'COSTOS', 'AVANZADO', 'NUEVA ENCUESTA', 'MARCAR UNA SOLA RESPUESTA', 1, 1, '2019-12-20', '2019-12-28'),
(17, '85', 'AUDITORIA', 'BASICO', 'JHJJHHJHJJHJH', 'GGHHGHGH', 1, 1, '2020-01-15', '2020-01-15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opciones`
--

CREATE TABLE `opciones` (
  `id_opcion` int(11) NOT NULL,
  `id_pregunta` int(11) NOT NULL,
  `valor` text COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `opciones`
--

INSERT INTO `opciones` (`id_opcion`, `id_pregunta`, `valor`) VALUES
(21, 12, 'LOS SOCIOS'),
(22, 12, 'INTERNOS'),
(23, 12, 'EXTERNOS'),
(24, 12, 'TODAS LAS ANTERIORES'),
(25, 13, 'EL CONSUMO VALORADO EN DINERO DE LOS BIENES Y SERVICIOS NECESARIO PARA LA PRODUCCIÓN'),
(26, 13, 'EL CONSUMO VALORADO EN DINERO DE LOS BIENES Y SERVICIOS NECESARIO PARA LA PRODUCCION'),
(27, 13, 'EL SACRIFICIO DE VALORES O SALIDA DE ACTIVOS DEL PATRIMONIO DE LA EMPRESA,'),
(28, 13, 'LO QUE HAY QUE DAR A CAMBIO DE ALGO. '),
(29, 14, 'CALCULAR LA MAGNITUD ECONOMICA DE UN COSTE. '),
(30, 14, 'CIFRAR UN CONSUMO DE FACTORES EN UNIDADES MONETARIAS. '),
(31, 14, 'CIFRAR LA MAGNITUD TECNICA DE UN COSTE'),
(32, 14, 'CIFRAR LA MAGNITUD TECNICA DE UN COSTE Y VALORARLO EN UNIDADES MONETARIAS'),
(33, 15, 'LA MATERIA PRIMA. '),
(34, 15, 'LOS CENTROS O SECCIONES.  '),
(35, 15, 'LOS PORTADORES EXCLUSIVAMENTE'),
(36, 15, 'LOS PRODUCTOS Y LOS CENTROS'),
(37, 16, 'LA MATERIA PRIMA Y LOS COSTES DE TRANSFORMACIÃ“N'),
(38, 16, ' LA MATERIA PRIMA, LA MANO DE OBRA DIRECTA Y LOS GASTOS GENERALES DE TRANSFORMACIÃ“N'),
(39, 16, 'LOS COSTES DE TRANSFORMACIÃ“N Y LOS DE DISTRIBUCIÃ“N'),
(40, 16, 'LOS COSTES DE TRANSFORMACIÃ“N, LOS DE DISTRIBUCIÃ“N Y LOS DE ADMINISTRACIÃ“N'),
(41, 17, 'UN COSTE DIRECTO SIEMPRE'),
(42, 17, 'UN COSTE DIRECTO O INDIRECTO'),
(43, 17, 'UN COSTE INDIRECTO SIEMPRE'),
(44, 17, 'UN COSTE FIJO'),
(49, 18, 'COSTES DIRECTOS DE PRODUCCIÃ“N. '),
(50, 18, 'COSTES DIRECTO DE ADMINISTRACIÃ“N.'),
(51, 18, 'COSTE INDIRECTO DE PRODUCCIÃ“N'),
(52, 18, 'NINGUNA DE LAS ANTERIORES.'),
(53, 19, 'LA NATURALEZA DEL COSTE. '),
(54, 19, 'EL OBJETO DEL COSTE DEL QUE ESTEMOS HABLANDO'),
(55, 19, 'SI SE TIENE O NO UNA MEDIDA INDIVIDUALIZADA'),
(56, 19, 'EL PRINCIPIO DE ECONOMICIDAD.'),
(57, 20, 'CIFRAR COSTES INDIRECTOS PARA IMPUTARLOS POSTERIORMENTE A OTROS CENTROS'),
(58, 20, ' CIFRAR COSTES INDIRECTOS PARA IMPUTARLOS POSTERIORMENTE A LOS PRODUCTOS'),
(59, 20, 'FIJAR UN RESPONSABLE DEL CENTRO PARA CONTROLAR LOS COSTES DEL MISMO'),
(60, 20, 'CIFRAR COSTES DIRECTOS DE PRODUCCIÓN PARA IMPUTARLOS POSTERIORMENTE A LA CUENTA DE RESULTADOS'),
(61, 21, 'LOS COSTES DE ADMINISTRACION HAN DE IMPUTAR SIEMPRE A RESULTADOS'),
(62, 21, 'LOS COSTES DE ADMINISTRACIÓN SE HAN DE IMPUTAR COMO UN COSTE MÁS AL PRODUCTO'),
(63, 21, 'LOS COSTES DE ADMINISTRACION SE PODRIAN IMPUTAR ENTERE LOS OTROS CENTROS O FUNCIONES'),
(64, 21, 'LOS COSTES DE ADMINISTRACIÓN NO SE HAN DE IMPUTAR A RESULTADOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `id_pregunta` int(11) NOT NULL,
  `id_encuesta` int(11) NOT NULL,
  `codigo` varchar(120) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `titulo` varchar(150) COLLATE utf8mb4_spanish_ci NOT NULL,
  `respuesta` varchar(220) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `id_tipo_pregunta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `preguntas`
--

INSERT INTO `preguntas` (`id_pregunta`, `id_encuesta`, `codigo`, `titulo`, `respuesta`, `id_tipo_pregunta`) VALUES
(12, 14, 'BA-001', ' LOS USUARIOS DE LA CONTABILIDAD DE COSTES SON: ', 'INTERNOS', 1),
(13, 14, 'BA-002', 'PODEMOS DEFINIR COSTE, TAL Y COMO INDICA PEDERSEN', 'EL CONSUMO VALORADO EN DINERO DE LOS BIENES Y SERVICIOS NECESARIO PARA LA PRODUCCION', 1),
(14, 14, 'BA-003', 'EXPRESAR UN COSTE EN LAS UNIDADES QUE LE SON PROPIAS O ESPECÃ???Ã?Â€ŠÃ‚ÂFICAS SUPONE: ', 'CIFRAR LA MAGNITUD TECNICA DE UN COSTE', 1),
(15, 14, 'BA-004', 'LOS OBJETOS DE COSTE EN UNA EMPRESA INDUSTRIAL PUEDEN SER:', 'LOS PRODUCTOS Y LOS CENTROS', 1),
(16, 14, 'BA-005', 'LOS ELEMENTOS INTEGRANTES DEL COSTE INDUSTRIAL SON: ', ' LA MATERIA PRIMA, LA MANO DE OBRA DIRECTA Y LOS GASTOS GENERALES DE TRANSFORMACIÃ“N', 1),
(17, 15, 'MD-001', 'LOS ELEMENTOS Y CONJUNTOS INCORPORABLES SE TRATAN COMO: ', 'UN COSTE DIRECTO SIEMPRE', 1),
(18, 15, 'MD-002 ', 'LOS COSTES ASOCIADOS AL ACTIVO FIJO DE UNA EMPRESA SON CONSIDERADOS:  ', 'COSTE INDIRECTO DE PRODUCCIÃ“N', 1),
(19, 15, 'MD-003', 'LA DISTINCION DE COSTE DIRECTO E INDIRECTO DEPENDE:', 'SI SE TIENE O NO UNA MEDIDA INDIVIDUALIZADA', 1),
(20, 15, 'MD-004', 'LOS CENTROS O SECCIONES PERMITEN:  ', ' CIFRAR COSTES INDIRECTOS PARA IMPUTARLOS POSTERIORMENTE A LOS PRODUCTOS', 1),
(21, 15, 'MD-005', 'DIGA SI ES VERDADERO O FALSO', 'LOS COSTES DE ADMINISTRACION SE PODRIAN IMPUTAR ENTERE LOS OTROS CENTROS O FUNCIONES', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resultados`
--

CREATE TABLE `resultados` (
  `id_resultado` int(11) NOT NULL,
  `id_opcion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_pregunta`
--

CREATE TABLE `tipo_pregunta` (
  `id_tipo_pregunta` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_pregunta`
--

INSERT INTO `tipo_pregunta` (`id_tipo_pregunta`, `nombre`, `descripcion`) VALUES
(1, 'Selección múltiple', 'Se podrá escoger solo una opción\r\nelemento input type radio'),
(2, 'Desplegable', 'Se podrá escoger una opción\r\nElemento select y option'),
(3, 'Texto', 'Se almacenara la respuesta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id_tipo_usuario` int(11) NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_tipo_usuario`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `clave` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombres` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellidos` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `telefono` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `id_tipo_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `clave`, `nombres`, `apellidos`, `email`, `telefono`, `id_tipo_usuario`) VALUES
('1750474048', 'usuario', 'LIS LIS ', 'ASASA', 'FDFDDF', 'dfdd', 1),
('1750474049', 'usuario', 'IVAN', 'DE LA CRUZ', '8777744', '0985255', 1),
('1752544', 'usuario', 'DFDFDFD', 'SDDSD', 'DSS', 'dfdd', 1),
('454', 'usuario', 'RRTRT', 'RTRTR', 'RTRRT', 'rtr', 1),
('4545', 'usuario', 'RERERE', '454', 'ERERE', 'erer', 1),
('565', 'usuario', 'TTYYT', 'TYYYT', 'TYYTTY', 'tyty', 1),
('ccc', 'usuario', 'CC', 'CC', '', '', 1),
('dfdf', 'usuario', 'DFD', 'DFD', '', '', 1),
('dfdfd', 'usuario', 'FDFDF', 'DFFDFD', 'DFDF', 'dfdf', 1),
('dfdfdfd', 'usuario', 'DFDFDDFDF', 'DFFDDF', 'FDFDFDDF', 'dffdfd', 1),
('erer', 'usuario', 'ERE', 'ERE', 'ERER', 'ere', 1),
('fgf', 'usuario', 'FGF', 'FGF', 'FGF', 'fgf', 1),
('GHG', 'usuario', 'GHG', 'GHG', '', '', 1),
('ghghhg', 'usuario', 'GHGHGHGHG', 'HGHGHG', 'GHHGGHHG', 'ghhghg', 1),
('hhj', 'usuario', 'HJ', 'HJH', 'HJH', 'hjh', 1),
('jhjhjh', 'usuario', 'HJJHH', 'HHHJ', 'HJHJH', 'hjjhhj', 1),
('kjkjjk', 'usuario', 'GHGGF', 'HGHG', '', '', 1),
('nmmnmnmnn', 'usuario', 'NMMNMNMN', 'NMMNNM', 'NNMMNMN', 'nmnmn', 1),
('nnnnhhg', 'usuario', 'NNNNN', 'BNBN', 'BNBNB', 'bnb', 1),
('rttrrt', 'usuario', 'RTTRTR', 'TTRTRTR', 'TRTRTRTTR', 'rtrttr', 1),
('wewew', 'usuario', 'WEW', 'WEW', 'WEW', 'wew', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_encuestas`
--

CREATE TABLE `usuarios_encuestas` (
  `id_usuario` varchar(15) COLLATE utf8mb4_spanish_ci NOT NULL,
  `id_encuesta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `calificaciones`
--
ALTER TABLE `calificaciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_encuesta` (`id_encuesta`);

--
-- Indices de la tabla `encuestas`
--
ALTER TABLE `encuestas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opciones`
--
ALTER TABLE `opciones`
  ADD PRIMARY KEY (`id_opcion`),
  ADD KEY `id_pregunta` (`id_pregunta`);

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`id_pregunta`),
  ADD KEY `id_tipo_pregunta` (`id_tipo_pregunta`),
  ADD KEY `id_encuesta` (`id_encuesta`);

--
-- Indices de la tabla `resultados`
--
ALTER TABLE `resultados`
  ADD PRIMARY KEY (`id_resultado`),
  ADD KEY `MANTIENE` (`id_opcion`);

--
-- Indices de la tabla `tipo_pregunta`
--
ALTER TABLE `tipo_pregunta`
  ADD PRIMARY KEY (`id_tipo_pregunta`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id_tipo_usuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `id_tipo_usuario` (`id_tipo_usuario`);

--
-- Indices de la tabla `usuarios_encuestas`
--
ALTER TABLE `usuarios_encuestas`
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_encuesta` (`id_encuesta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `calificaciones`
--
ALTER TABLE `calificaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `encuestas`
--
ALTER TABLE `encuestas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `opciones`
--
ALTER TABLE `opciones`
  MODIFY `id_opcion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `id_pregunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `resultados`
--
ALTER TABLE `resultados`
  MODIFY `id_resultado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tipo_pregunta`
--
ALTER TABLE `tipo_pregunta`
  MODIFY `id_tipo_pregunta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id_tipo_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `calificaciones`
--
ALTER TABLE `calificaciones`
  ADD CONSTRAINT `calificaciones_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `calificaciones_ibfk_2` FOREIGN KEY (`id_encuesta`) REFERENCES `encuestas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `opciones`
--
ALTER TABLE `opciones`
  ADD CONSTRAINT `opciones_ibfk_1` FOREIGN KEY (`id_pregunta`) REFERENCES `preguntas` (`id_pregunta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `opciones_ibfk_2` FOREIGN KEY (`id_pregunta`) REFERENCES `preguntas` (`id_pregunta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD CONSTRAINT `preguntas_ibfk_1` FOREIGN KEY (`id_tipo_pregunta`) REFERENCES `tipo_pregunta` (`id_tipo_pregunta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `preguntas_ibfk_2` FOREIGN KEY (`id_tipo_pregunta`) REFERENCES `tipo_pregunta` (`id_tipo_pregunta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `preguntas_ibfk_3` FOREIGN KEY (`id_encuesta`) REFERENCES `encuestas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `resultados`
--
ALTER TABLE `resultados`
  ADD CONSTRAINT `MANTIENE` FOREIGN KEY (`id_opcion`) REFERENCES `opciones` (`id_opcion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario` (`id_tipo_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios_encuestas`
--
ALTER TABLE `usuarios_encuestas`
  ADD CONSTRAINT `usuarios_encuestas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuarios_encuestas_ibfk_2` FOREIGN KEY (`id_encuesta`) REFERENCES `encuestas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
