import serial
import time
import pygame
import pygame.camera

pygame.camera.init()
cam = pygame.camera.Camera(pygame.camera.list_cameras()[0])
port = serial.Serial(port='COM8', baudrate=9600,timeout=1.0)
#message = closed
img_cnt = 0
filestr = 'photo'
file_ext='.bmp'
while True:
    rcv = port.read(16)
    a=(repr(rcv))
    if a=="b'Door closed'":
        cam.start()
        img=cam.get_image()
        import pygame.image
        img_cnt_str=str(img_cnt)
        filename="".join((filestr,img_cnt_str,file_ext))
        pygame.image.save(img, filename)
        img_cnt = img_cnt+1
        pygame.camera.quit
        #port.flushInput()
    else:
        time.sleep(1)
        continue
