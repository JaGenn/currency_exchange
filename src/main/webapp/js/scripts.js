function deleteCurrency(id) {
                fetch('/currencies?id=' + id, {
                    method: 'DELETE'
                })
                .then(response => {
                        window.location.reload();
                })
                .catch(error => console.error('Ошибка:', error));
            }
function updateRate(event, currencyCode) {
                                event.preventDefault();

                                      const rate = event.target.querySelector('input[name="rate"]').value;
                                      const encodedCurrency = encodeURIComponent(currencyCode.trim()); // Удаляем лишние пробелы

                                      fetch(`/exchangeRate/${encodedCurrency}`, {
                                          method: "POST",
                                          body: new URLSearchParams({ rate: rate, currencies: currencyCode }),
                                          headers: { "Content-Type": "application/x-www-form-urlencoded" }
                                      })
                                      .then(response => {

                                              event.target.reset(); // Очистка формы
                                              location.reload();

                                      })
                                      .catch(error => alert("Ошибка сети: " + error.message));
                                  }