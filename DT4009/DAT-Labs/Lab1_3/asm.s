      NAME main
      PUBLIC main
      SECTION .text: CODE (2)
      THUMB
main
     MOV R6, # 0
     MOV R7, # 0
     MOV R8, # 0
     LDR R0, =Vector
     
STOP B STOP
     ALIGNROM 2
     DATA
Vector
     DC32 14, 25, 2, 27, 3
     DC32 22, 13, 4, 24, 6
     DC32 26, 18, 8, 15, 9
     DC32 28, 10, 7, 17, 5
     
     END 