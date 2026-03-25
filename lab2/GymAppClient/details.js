const API_URL = 'https://localhost:7196/api/memberships';
const urlParams = new URLSearchParams(window.location.search);
const membershipId = urlParams.get('id');

document.addEventListener('DOMContentLoaded', () => {
    if (membershipId) {
        document.getElementById('pageTitle').innerText = 'Редагування абонемента';
        loadMembership(membershipId);
    }
});

async function loadMembership(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) throw new Error('Запис не знайдено');
        
        const m = await response.json();
        
        document.getElementById('id').value = m.id;
        document.getElementById('membershipType').value = m.membershipType;
        document.getElementById('issueDate').value = m.issueDate ? m.issueDate.slice(0, 16) : '';
        document.getElementById('expirationDate').value = m.expirationDate ? m.expirationDate.slice(0, 16) : '';
        document.getElementById('price').value = m.price;
        document.getElementById('gymId').value = m.gymId;
        document.getElementById('visitorId').value = m.visitorId;

        if (m.gym || m.visitor) {
            document.getElementById('relatedInfoCard').style.display = 'block';
            
            if (m.gym) {
                document.getElementById('gymDetailsText').innerHTML = 
                    `<strong>Назва:</strong> ${m.gym.name}<br>
                     <strong>Адреса:</strong> ${m.gym.address}`;
            }
            
            if (m.visitor) {
                document.getElementById('visitorDetailsText').innerHTML = 
                    `<strong>ПІБ:</strong> ${m.visitor.firstName} ${m.visitor.lastName}<br>
                     <strong>Тренування:</strong> ${m.visitor.workoutRoutine || 'Не вказано'}<br>
                     <strong>Дієта:</strong> ${m.visitor.dietaryNotes || 'Не вказано'}`;
            }
        }
    } catch (error) {
        document.getElementById('errorBox').innerText = error.message;
        document.getElementById('errorBox').style.display = 'block';
    }
}

document.getElementById('membershipForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const id = parseInt(document.getElementById('id').value) || 0;
    const payload = {
        id: id,
        membershipType: document.getElementById('membershipType').value,
        issueDate: document.getElementById('issueDate').value,
        expirationDate: document.getElementById('expirationDate').value,
        price: parseFloat(document.getElementById('price').value),
        gymId: parseInt(document.getElementById('gymId').value),
        visitorId: parseInt(document.getElementById('visitorId').value)
    };

    const method = id === 0 ? 'POST' : 'PUT';
    const url = id === 0 ? API_URL : `${API_URL}/${id}`;

    try {
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            window.location.href = 'index.html';
        } else {
            const errorText = await response.text();
            document.getElementById('errorBox').innerText = errorText || 'Помилка збереження даних.';
        }
    } catch (error) {
        document.getElementById('errorBox').innerText = 'Помилка з\'єднання з сервером.';
    }
});