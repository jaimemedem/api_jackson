function deleteContacto(email, row) {
  fetch(`/api/mensajes/${email}`, { method: 'DELETE' })
    .then(r => {
      if (!r.ok) throw new Error('Error al eliminar')
      return r.text()
    })
    .then(() => {
      row.remove()
    })
    .catch(e => console.error(e))
}

function loadContactos() {
  fetch('/api/mensajes/all')
    .then(response => response.json())
    .then(data => {
      const tbody = document.querySelector('#tablaContactos tbody')
      tbody.innerHTML = ''
      data.forEach(contacto => {
        const row = document.createElement('tr')

        const tdName = document.createElement('td')
        tdName.textContent = contacto.name

        const tdEmail = document.createElement('td')
        tdEmail.textContent = contacto.email

        const tdNumber = document.createElement('td')
        tdNumber.textContent = contacto.number

        const tdMensaje = document.createElement('td')
        tdMensaje.textContent = contacto.mensaje

        const tdAcciones = document.createElement('td')
        const btnEliminar = document.createElement('button')
        btnEliminar.textContent = 'Eliminar'

        // 'deleteContacto(contacto.email, row)' pasa el email y la fila
        btnEliminar.addEventListener('click', () => {
          deleteContacto(contacto.email, row)
        })

        tdAcciones.appendChild(btnEliminar)

        row.appendChild(tdName)
        row.appendChild(tdEmail)
        row.appendChild(tdNumber)
        row.appendChild(tdMensaje)
        row.appendChild(tdAcciones)
        tbody.appendChild(row)
      })
    })
    .catch(error => console.error(error))
}

function enviarContacto() {
  const name = document.getElementById('name').value
  const email = document.getElementById('email').value
  const number = document.getElementById('number').value
  const mensaje = document.getElementById('mensaje').value

  const data = { name, email, number, mensaje }

  fetch('/api/mensajes', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  })
    .then(r => {
      if (!r.ok) throw new Error('Error al enviar contacto')
      return r.json()
    })
    .then(resultado => {
      console.log('Contacto creado:', resultado)
      document.getElementById('name').value = ''
      document.getElementById('email').value = ''
      document.getElementById('number').value = ''
      document.getElementById('mensaje').value = ''
    })
    .catch(err => console.error(err))
}
