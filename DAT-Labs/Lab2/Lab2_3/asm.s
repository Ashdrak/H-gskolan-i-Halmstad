        NAME    main
        PUBLIC  main
        SECTION .text : CODE (2)
        THUMB

FIB     PUSH {LR}
        CMP R0, #0 ; when R0 = 0 POP program counter 
        BGT FIB_DO ; jump to FIB_DO
        POP {PC}   ; jump to the last adress of a linked branch ??

FIB_DO 
        ADD R1, R1, R2 ; f = f + g
        SUB R2, R1, R2 ; g = f - g
        SUB R0, R0, #1 ; n = n - 1
        BL FIB         ; jump to fib
        POP {PC}       ; Finds which adress the program branched from ??

main
       MOV R0, #6 ; n
       MOV R1, #0 ;f
       MOV R2, #1 ;g
       BL FIB
STOP B STOP ; infinit walk
       END
