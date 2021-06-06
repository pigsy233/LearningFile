clc;
close;
clear;

slit = imread('10-807J.jpg');
slit = im2gray(slit);
slit = imgaussfilt(slit,5);
% slit = imfilter(slit,fspecial('average',[3,3]));
imhist(slit);
[Tf SMf] = graythresh(slit);
Pic2 = im2bw(slit, SMf - 0.2); 


figure(1)
subplot(2,2,1);
imshow(slit);
title('降噪后图像')
subplot(222)
imshow(Pic2)
title('二值化图像')

%用edge算法对二值化图像进行边缘提取

Pic2 = im2bw(slit, SMf - 0.1); 

PicEdge2 = edge(Pic2,'sobel',0.1);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.2);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.3);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.4);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.5);
figure();
imshow(PicEdge2);
title('sobel算子');

Pic2 = im2bw(slit, SMf - 0.125); 

PicEdge2 = edge(Pic2,'sobel',0.1);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.2);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.3);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.4);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.5);
figure();
imshow(PicEdge2);
title('sobel算子');

Pic2 = im2bw(slit, SMf - 0.15); 

PicEdge2 = edge(Pic2,'sobel',0.1);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.2);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.3);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.4);
figure();
imshow(PicEdge2);
title('sobel算子');

PicEdge2 = edge(Pic2,'sobel',0.5);
figure();
imshow(PicEdge2);
title('sobel算子');

% PicEdge1=edge(Pic2,'log');
% subplot(223);
% imshow(PicEdge1);
% title('log算子');
% 
% PicEdge2 = edge(Pic2,'canny');
% subplot(224);
% imshow(PicEdge2);
% title('canny算子');
% 
% PicEdge3=edge(Pic2,'sobel');
% figure(2)
% subplot(221)
% imshow(PicEdge3);
% title('sobel算子')
% 
% PicEdge4=edge(Pic2,'prewitt');
% subplot(222)
% imshow(PicEdge4);
% title('sprewitt算子')
% 
% PicEdge5=edge(Pic2,'zerocross');
% subplot(223)
% imshow(PicEdge5);
% title('zerocross算子')
% 
% PicEdge6=edge(Pic2,'roberts');
% subplot(224)
% imshow(PicEdge6);
% title('roberts算子')

