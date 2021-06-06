clc;
clear;

slit = imread('10-807J.jpg');
slit = im2gray(slit);
slit = medfilt2(slit,[5,5]);
imhist(slit);
h_slit = imhist(slit);
[Tf SMf] = graythresh(slit);
g_slit = im2bw(slit, Tf);
figure,imshow(g_slit);

%读取一张图片，并显示

Pic2=im2bw(g_slit,SMf);
figure(1)
subplot(2,2,1);
imshow(g_slit);
title('原始RGB图像')
subplot(222)
imshow(Pic2)
title('二值化图像')

%用edge算法对二值化图像进行边缘提取
PicEdge1=edge(Pic2,'log');
subplot(223);
imshow(PicEdge1);
title('log算子')

PicEdge2 = edge(Pic2,'canny');
subplot(224);
imshow(PicEdge2);
title('canny算子');

PicEdge3=edge(Pic2,'sobel');
figure(2)
subplot(221)
imshow(PicEdge3);
title('sobel算子')

PicEdge4=edge(Pic2,'prewitt');
subplot(222)
imshow(PicEdge4);
title('sprewitt算子')

PicEdge5=edge(Pic2,'zerocross');
subplot(223)
imshow(PicEdge5);
title('zerocross算子')

PicEdge6=edge(Pic2,'roberts');
subplot(224)
imshow(PicEdge6);
title('roberts算子')

