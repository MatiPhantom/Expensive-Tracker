# 💸 Expense Tracker – Rastreador de Gastos en Línea de Comandos

**Expense Tracker** es una aplicación de línea de comandos desarrollada en Java que te permite registrar, consultar y administrar tus gastos de manera sencilla y rápida, directamente desde tu terminal.

Ideal para quienes prefieren mantener el control financiero sin depender de aplicaciones sobrecargadas o conectividad constante.

---

## 🚀 Características

- 🧾 **Registro de gastos** usando comandos simples con soporte para tokens y comillas.
- 📂 **Almacenamiento en formato JSON**, estructurado y fácil de leer/modificar.
- 🔍 **Filtros personalizados** por fecha, categoría, monto u otros criterios.
- 🕹️ **Uso fluido desde la terminal**, sin necesidad de interfaz gráfica.
- ⚙️ **Diseño modular** con separación de lógica, persistencia y parsing de comandos.
- 📦 Preparado para integrarse con gráficos, reportes u otras funcionalidades a futuro.

---

## 🛠️ Tecnologías utilizadas

- **Java 17+**
- **Jackson** (para serialización/deserialización JSON)
- **Patrones DAO y separación de capas**
- Uso de clases `record`, `Scanner`, y manejo de archivos con `FileWriter` y `Paths`
