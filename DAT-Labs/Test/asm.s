        NAME    main
        PUBLIC  main
        SECTION .text : CODE (2)
        THUMB


FIB     PUSH {R1, R2, LR}
        CMP R0, #0
        BGT FIB_DO 
        POP {R1, R2, PC}

FIB_DO 
        ADD R1, R1, R2
        SUB R2, R1, R2
        SUB R0, R0, #1
        BL FIB
        ADD R4, R1, R2
        POP {R1, R2, PC}

main
       MOV R0, #6 ; n
       MOV R1, #0 ;f
       MOV R2, #1 ;g
       BL FIB
STOP B STOP
       END

