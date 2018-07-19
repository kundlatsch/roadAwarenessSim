// Initial state

approaching(car).
!be(safe).
!interact(smartphone).

// Plans

+!be(safe) : approaching(car) <- change(lane); ?approaching(car).

+interact(smartphone) : not approaching(car) <- press(button).

