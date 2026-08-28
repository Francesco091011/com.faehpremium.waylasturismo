# Plan de Desarrollo por Fases - Waylas Turismo

Este plan detalla el camino para completar las funcionalidades pendientes de la aplicación Waylas Turismo, organizadas en fases lógicas para asegurar una base sólida antes de escalar a servicios complejos.

## User Review Required

> [!IMPORTANT]
> Algunas fases requieren la obtención de API Keys externas (Google Maps, OpenWeather, etc.) que deberán ser proporcionadas o configuradas en secretos del proyecto.

> [!WARNING]
> La integración de AdMob y sistemas de pago requiere cuentas de desarrollador activas y configuración en las consolas respectivas.

## Proposed Changes

### Fase 1: Base de UI y Contenido Estático
Objetivo: Eliminar los "pantallazos vacíos" y proporcionar una experiencia de usuario completa, aunque sea con datos locales iniciales.

#### [MODIFY] [ArchaeologyScreen.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/ui/ArchaeologyScreen.kt)
- Implementar lista de centros arqueológicos.
#### [MODIFY] [RestaurantsScreen.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/ui/RestaurantsScreen.kt)
- Implementar listado y categorías de restaurantes.
#### [MODIFY] [RoutesScreen.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/ui/RoutesScreen.kt)
- Definir rutas turísticas recomendadas.
#### [MODIFY] [ToursScreen.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/ui/ToursScreen.kt)
- Implementar catálogo de tours.
#### [MODIFY] [CalendarScreen.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/ui/CalendarScreen.kt)
- Implementar visualización de mejores fechas para viajar.

---

### Fase 2: Integración de Servicios Externos
Objetivo: Conectar la app con el mundo real mediante APIs.

#### [MODIFY] [build.gradle](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/build.gradle)
- Agregar dependencias para Google Maps SDK, Retrofit (para Clima/Tipo de Cambio).
#### [MODIFY] [DestinationsScreen.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/ui/DestinationsScreen.kt)
- Implementar navegación real al mapa y visualización de puntos de interés.

---

### Fase 3: Módulos de Negocio y Transacciones
Objetivo: Habilitar la monetización directa y servicios de terceros.

#### [MODIFY] [ShopManager.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/shop/ShopManager.kt)
- Lógica de venta de productos digitales.
#### [MODIFY] [MarketplaceManager.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/marketplace/MarketplaceManager.kt)
- Sistema de reservas y publicación de servicios.
#### [MODIFY] [PremiumManager.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/premium/PremiumManager.kt)
- Control de acceso y suscripciones.

---

### Fase 4: Comunidad y Engagement
Objetivo: Fomentar la retención y el contenido generado por el usuario.

#### [MODIFY] [UGCManager.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/ugc/UGCManager.kt)
- Subida de fotos y reseñas.
#### [MODIFY] [GamificationManager.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/gamification/GamificationManager.kt)
- Sistema de puntos y logros.
#### [MODIFY] [NotificationsManager.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/notifications/NotificationsManager.kt)
- Configuración de Firebase Cloud Messaging.

---

### Fase 5: Monetización y Escalabilidad
Objetivo: Publicidad y soporte global.

#### [MODIFY] [AdsManager.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/ads/AdsManager.kt)
- Integración de banners e intersticiales de AdMob.
#### [MODIFY] [I18nManager.kt](file:///D:/AProgramA/com.faehpremium.waylasturismo/app/src/main/java/com/faehpremium/waylasturismo/i18n/I18nManager.kt)
- Soporte multilingüe completo (Strings, Formatos).

## Verification Plan

### Automated Tests
- Pruebas unitarias para los Managers de lógica de negocio.
- UI Tests para verificar la navegación entre todas las nuevas pantallas.

### Manual Verification
- Probar la integración de mapas en un dispositivo real/emulador con Play Services.
- Simular compras en el módulo Premium.
