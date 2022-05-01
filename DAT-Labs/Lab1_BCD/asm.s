        NAME    main
        PUBLIC  main
        SECTION .text : CODE (2)
        THUMB
main
 
     MOV R0, # 0        ; (R0) <0
     MOV R1, # 0        ; (R1) <0
     MOV R2, # 0        ; (R2) <0
     MOV R3, # 0x999    ; (R3) <0x999
LOOP CMP R2, # 99       ; compare (R2) to 99
     BNE CMP0           ; different> jump to CMP0
     ADD R0, R0, # 103    ; increment (R0) by 103
     MOV R1, # 0        ; equal> restart
     MOV R2, # 0        ; equal> restart
     B LOP2             ; jump to LOP2
CMP0 CMP R1, # 9        ; compare (R2)
     BNE ADD0           ; different> jump to ADD0
     MOV R1, # 0        ; (R1) <0
     ADD R0, R0, # 7    ; increment (R0) by 7
     ADD R2, R2, # 1    ; increment (R2) by 1
     B LOP2             ; jump to LOP2
ADD0 ADD R0, R0, # 1    ;  update first: (R0) <(R0) + 1
     ADD R1, R1, # 1    ; update first: (R1) <(R1) + 1
     ADD R2, R2, # 1    ; update first: (R2) <(R2) + 1
LOP2 CMP R0, R3         ; compare (R0) to 0x999
     BNE LOOP           ; different > jump to LOOP, repeat
STOP B STOP             ; infinite walk
     END