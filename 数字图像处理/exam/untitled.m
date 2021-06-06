clear
close all;
ps=imread('10-258Z1.jpg');
subplot(121),imshow(ps)
background=imopen(ps,strel('disk',4));
% imshow(background);
subplot(122),surf(double(background(1:4:end,1:4:end))),zlim([0 256]);
set(gca,'Ydir','reverse');
% ps2=imsubtract(ps,background);
% figure,imshow(ps2)
% axis([0 280 60 260])
% plot(ps(1:280,110));

clc,clear,close all
img=imread('1.jpg');
subplot(121),imshow(img),xlabel('原始图像')
img_1=img(:,:,1);
img_2=img(:,:,2);
img_3=img(:,:,3);
Y=0.299*img_1+0.587*img_2+0.114*img_3;  % 白平衡系数
[m,n]=size(Y);
k=Y(1,1);
for i=1:m
    for j=1:n
        if Y(i,j)>=k
            k=Y(i,j);
            k1=i;
            k2=j;
        end
    end
end
