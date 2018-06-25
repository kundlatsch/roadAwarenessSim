!survive.
+!survive <- move; .sleep(2000); !other.
+!other <- !survive.
+!handle(gold(X,Y))
  :  not free
  <- .print("Handling ",gold(X,Y)," now.");
     .broadcast(tell, committed_to(gold(X,Y)));
     !pos(X,Y);
     !ensure(pick,gold(X,Y));
     .broadcast(tell,picked(gold(X,Y)));
     ?depot(_,DX,DY);
     !pos(DX,DY);
     !ensure(drop, 0);
     -gold(X,Y)[source(_)];
     .print("Finish handling ",gold(X,Y));
     !!choose_gold.

