import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import tech.icey.glfw.GLFW;
import tech.icey.glfw.GLFWConstants;
import tech.icey.glfw.GLFWLoader;
import tech.icey.glfw.handle.GLFWwindow;
import tech.icey.panama.Loader;
import tech.icey.panama.NativeLayout;
import tech.icey.panama.annotation.enumtype;
import tech.icey.panama.annotation.pointer;
import tech.icey.panama.buffer.ByteBuffer;
import tech.icey.panama.buffer.FloatBuffer;
import tech.icey.panama.buffer.IntBuffer;
import tech.icey.panama.buffer.LongBuffer;
import tech.icey.panama.buffer.PointerBuffer;
import tech.icey.vk4j.Constants;
import tech.icey.vk4j.Version;
import tech.icey.vk4j.VulkanLoader;
import tech.icey.vk4j.bitmask.VkAccessFlags;
import tech.icey.vk4j.bitmask.VkBufferUsageFlags;
import tech.icey.vk4j.bitmask.VkColorComponentFlags;
import tech.icey.vk4j.bitmask.VkCommandBufferUsageFlags;
import tech.icey.vk4j.bitmask.VkCommandPoolCreateFlags;
import tech.icey.vk4j.bitmask.VkCompositeAlphaFlagsKHR;
import tech.icey.vk4j.bitmask.VkCullModeFlags;
import tech.icey.vk4j.bitmask.VkDebugUtilsMessageSeverityFlagsEXT;
import tech.icey.vk4j.bitmask.VkDebugUtilsMessageTypeFlagsEXT;
import tech.icey.vk4j.bitmask.VkFenceCreateFlags;
import tech.icey.vk4j.bitmask.VkFormatFeatureFlags;
import tech.icey.vk4j.bitmask.VkImageAspectFlags;
import tech.icey.vk4j.bitmask.VkImageUsageFlags;
import tech.icey.vk4j.bitmask.VkMemoryPropertyFlags;
import tech.icey.vk4j.bitmask.VkPipelineStageFlags;
import tech.icey.vk4j.bitmask.VkQueueFlags;
import tech.icey.vk4j.bitmask.VkResolveModeFlags;
import tech.icey.vk4j.bitmask.VkSampleCountFlags;
import tech.icey.vk4j.bitmask.VkShaderStageFlags;
import tech.icey.vk4j.command.DeviceCommands;
import tech.icey.vk4j.command.EntryCommands;
import tech.icey.vk4j.command.InstanceCommands;
import tech.icey.vk4j.command.StaticCommands;
import tech.icey.vk4j.datatype.VkApplicationInfo;
import tech.icey.vk4j.datatype.VkBufferCopy;
import tech.icey.vk4j.datatype.VkBufferCreateInfo;
import tech.icey.vk4j.datatype.VkBufferImageCopy;
import tech.icey.vk4j.datatype.VkCommandBufferAllocateInfo;
import tech.icey.vk4j.datatype.VkCommandBufferBeginInfo;
import tech.icey.vk4j.datatype.VkCommandPoolCreateInfo;
import tech.icey.vk4j.datatype.VkDebugUtilsMessengerCallbackDataEXT;
import tech.icey.vk4j.datatype.VkDebugUtilsMessengerCreateInfoEXT;
import tech.icey.vk4j.datatype.VkDescriptorBufferInfo;
import tech.icey.vk4j.datatype.VkDescriptorImageInfo;
import tech.icey.vk4j.datatype.VkDescriptorPoolCreateInfo;
import tech.icey.vk4j.datatype.VkDescriptorPoolSize;
import tech.icey.vk4j.datatype.VkDescriptorSetAllocateInfo;
import tech.icey.vk4j.datatype.VkDescriptorSetLayoutBinding;
import tech.icey.vk4j.datatype.VkDescriptorSetLayoutCreateInfo;
import tech.icey.vk4j.datatype.VkDeviceCreateInfo;
import tech.icey.vk4j.datatype.VkDeviceQueueCreateInfo;
import tech.icey.vk4j.datatype.VkExtensionProperties;
import tech.icey.vk4j.datatype.VkExtent2D;
import tech.icey.vk4j.datatype.VkFenceCreateInfo;
import tech.icey.vk4j.datatype.VkFormatProperties;
import tech.icey.vk4j.datatype.VkGraphicsPipelineCreateInfo;
import tech.icey.vk4j.datatype.VkImageBlit;
import tech.icey.vk4j.datatype.VkImageCreateInfo;
import tech.icey.vk4j.datatype.VkImageMemoryBarrier;
import tech.icey.vk4j.datatype.VkImageViewCreateInfo;
import tech.icey.vk4j.datatype.VkInstanceCreateInfo;
import tech.icey.vk4j.datatype.VkLayerProperties;
import tech.icey.vk4j.datatype.VkPhysicalDeviceDynamicRenderingFeatures;
import tech.icey.vk4j.datatype.VkPhysicalDeviceFeatures;
import tech.icey.vk4j.datatype.VkPhysicalDeviceMemoryProperties;
import tech.icey.vk4j.datatype.VkPhysicalDeviceProperties;
import tech.icey.vk4j.datatype.VkPipelineColorBlendAttachmentState;
import tech.icey.vk4j.datatype.VkPipelineColorBlendStateCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineDepthStencilStateCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineDynamicStateCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineInputAssemblyStateCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineLayoutCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineMultisampleStateCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineRasterizationStateCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineRenderingCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineShaderStageCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineVertexInputStateCreateInfo;
import tech.icey.vk4j.datatype.VkPipelineViewportStateCreateInfo;
import tech.icey.vk4j.datatype.VkPresentInfoKHR;
import tech.icey.vk4j.datatype.VkQueueFamilyProperties;
import tech.icey.vk4j.datatype.VkRect2D;
import tech.icey.vk4j.datatype.VkRenderingAttachmentInfo;
import tech.icey.vk4j.datatype.VkRenderingInfo;
import tech.icey.vk4j.datatype.VkSamplerCreateInfo;
import tech.icey.vk4j.datatype.VkSemaphoreCreateInfo;
import tech.icey.vk4j.datatype.VkShaderModuleCreateInfo;
import tech.icey.vk4j.datatype.VkSubmitInfo;
import tech.icey.vk4j.datatype.VkSurfaceCapabilitiesKHR;
import tech.icey.vk4j.datatype.VkSurfaceFormatKHR;
import tech.icey.vk4j.datatype.VkSwapchainCreateInfoKHR;
import tech.icey.vk4j.datatype.VkVertexInputAttributeDescription;
import tech.icey.vk4j.datatype.VkVertexInputBindingDescription;
import tech.icey.vk4j.datatype.VkViewport;
import tech.icey.vk4j.datatype.VkWriteDescriptorSet;
import tech.icey.vk4j.enumtype.VkAttachmentLoadOp;
import tech.icey.vk4j.enumtype.VkAttachmentStoreOp;
import tech.icey.vk4j.enumtype.VkBlendFactor;
import tech.icey.vk4j.enumtype.VkBlendOp;
import tech.icey.vk4j.enumtype.VkBorderColor;
import tech.icey.vk4j.enumtype.VkColorSpaceKHR;
import tech.icey.vk4j.enumtype.VkCommandBufferLevel;
import tech.icey.vk4j.enumtype.VkCompareOp;
import tech.icey.vk4j.enumtype.VkDescriptorType;
import tech.icey.vk4j.enumtype.VkDynamicState;
import tech.icey.vk4j.enumtype.VkFilter;
import tech.icey.vk4j.enumtype.VkFormat;
import tech.icey.vk4j.enumtype.VkFrontFace;
import tech.icey.vk4j.enumtype.VkImageLayout;
import tech.icey.vk4j.enumtype.VkImageTiling;
import tech.icey.vk4j.enumtype.VkImageType;
import tech.icey.vk4j.enumtype.VkImageViewType;
import tech.icey.vk4j.enumtype.VkIndexType;
import tech.icey.vk4j.enumtype.VkLogicOp;
import tech.icey.vk4j.enumtype.VkPipelineBindPoint;
import tech.icey.vk4j.enumtype.VkPolygonMode;
import tech.icey.vk4j.enumtype.VkPresentModeKHR;
import tech.icey.vk4j.enumtype.VkPrimitiveTopology;
import tech.icey.vk4j.enumtype.VkResult;
import tech.icey.vk4j.enumtype.VkSamplerAddressMode;
import tech.icey.vk4j.enumtype.VkSamplerMipmapMode;
import tech.icey.vk4j.enumtype.VkSharingMode;
import tech.icey.vk4j.enumtype.VkVertexInputRate;
import tech.icey.vk4j.handle.VkBuffer;
import tech.icey.vk4j.handle.VkCommandBuffer;
import tech.icey.vk4j.handle.VkCommandPool;
import tech.icey.vk4j.handle.VkDebugUtilsMessengerEXT;
import tech.icey.vk4j.handle.VkDescriptorPool;
import tech.icey.vk4j.handle.VkDescriptorSet;
import tech.icey.vk4j.handle.VkDescriptorSetLayout;
import tech.icey.vk4j.handle.VkDevice;
import tech.icey.vk4j.handle.VkFence;
import tech.icey.vk4j.handle.VkImage;
import tech.icey.vk4j.handle.VkImageView;
import tech.icey.vk4j.handle.VkInstance;
import tech.icey.vk4j.handle.VkPhysicalDevice;
import tech.icey.vk4j.handle.VkPipeline;
import tech.icey.vk4j.handle.VkPipelineLayout;
import tech.icey.vk4j.handle.VkQueue;
import tech.icey.vk4j.handle.VkSampler;
import tech.icey.vk4j.handle.VkSemaphore;
import tech.icey.vk4j.handle.VkShaderModule;
import tech.icey.vk4j.handle.VkSurfaceKHR;
import tech.icey.vk4j.handle.VkSwapchainKHR;
import tech.icey.vma.VMA;
import tech.icey.vma.VMAJavaTraceUtil;
import tech.icey.vma.VMAUtil;
import tech.icey.vma.bitmask.VmaAllocationCreateFlags;
import tech.icey.vma.datatype.VmaAllocationCreateInfo;
import tech.icey.vma.datatype.VmaAllocationInfo;
import tech.icey.vma.datatype.VmaAllocatorCreateInfo;
import tech.icey.vma.datatype.VmaVulkanFunctions;
import tech.icey.vma.enumtype.VmaMemoryUsage;
import tech.icey.vma.handle.VmaAllocation;
import tech.icey.vma.handle.VmaAllocator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.nio.ByteOrder;
import java.util.Objects;

public class Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final ByteBuffer WINDOW_TITLE = ByteBuffer.allocateString(Arena.global(), "Vulkan");
    private static final String MODEL_PATH = "/model/viking_room.obj";
    private static final String TEXTURE_PATH = "/texture/viking_room.png";
    private static final boolean ENABLE_VALIDATION_LAYERS = System.getProperty("validation") != null;
    private static String VALIDATION_LAYER_NAME = "VK_LAYER_KHRONOS_validation";
    private static final int MAX_FRAMES_IN_FLIGHT = 2;
//    private static final float[] VERTICES = {
//            // vec3 pos         // vec3 color       // vec2 texCoord
//            -0.5f, -0.5f, 0.0f,   1.0f, 0.0f, 0.0f,   1.0f, 0.0f,
//            0.5f, -0.5f, 0.0f,    0.0f, 1.0f, 0.0f,   0.0f, 0.0f,
//            0.5f, 0.5f, 0.0f,     0.0f, 0.0f, 1.0f,   0.0f, 1.0f,
//            -0.5f, 0.5f, 0.0f,    1.0f, 1.0f, 1.0f,   1.0f, 1.0f,
//
//            -0.5f, -0.5f, -0.5f,   1.0f, 0.0f, 0.0f,   1.0f, 0.0f,
//            0.5f, -0.5f, -0.5f,    0.0f, 1.0f, 0.0f,   0.0f, 0.0f,
//            0.5f, 0.5f, -0.5f,     0.0f, 0.0f, 1.0f,   0.0f, 1.0f,
//            -0.5f, 0.5f, -0.5f,    1.0f, 1.0f, 1.0f,   1.0f, 1.0f
//    };
//    private static final short[] INDICES = {
//            0, 1, 2,
//            2, 3, 0,
//
//            4, 5, 6,
//            6, 7, 4
//    };
    private static final long startTime = System.currentTimeMillis();

    private static final FunctionDescriptor DESCRIPTOR_debugCallback = FunctionDescriptor.of(
            ValueLayout.JAVA_INT, // return value VkBool32
            ValueLayout.JAVA_INT, // int messageSeverity
            ValueLayout.JAVA_INT, // int messageType
            ValueLayout.ADDRESS, // const VkDebugUtilsMessengerCallbackDataEXT* pCallbackData
            ValueLayout.ADDRESS  // void* pUserData
    );

    private static final MethodHandle HANDLE_debugCallback;
    static {
        try {
            HANDLE_debugCallback = MethodHandles
                    .lookup()
                    .findStatic(Application.class, "debugCallback", DESCRIPTOR_debugCallback.toMethodType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final MemorySegment UPCALL_debugCallback = Linker
            .nativeLinker()
            .upcallStub(HANDLE_debugCallback, DESCRIPTOR_debugCallback, Arena.global());

    private Arena applicationArena = Arena.ofShared();
    private GLFW glfw;
    private GLFWwindow window;
    private StaticCommands staticCommands;
    private EntryCommands entryCommands;
    private VkInstance instance;
    private InstanceCommands instanceCommands;
    private VkDebugUtilsMessengerEXT debugMessenger;
    private VkSurfaceKHR surface;
    private VkPhysicalDevice physicalDevice;
    private VkDevice device;
    private @enumtype(VkSampleCountFlags.class) int msaaSamples;
    private DeviceCommands deviceCommands;
    private VkQueue graphicsQueue;
    private VkQueue presentQueue;
    private VMA vma;
    private VmaAllocator vmaAllocator;
    private VkSwapchainKHR swapChain;
    private VkImage[] swapChainImages;
    private @enumtype(VkFormat.class) int swapChainImageFormat;
    private VkExtent2D swapChainExtent;
    private VkImageView[] swapChainImageViews;
    private VkDescriptorSetLayout descriptorSetLayout;
    private VkPipelineLayout pipelineLayout;
    private VkPipeline graphicsPipeline;
    private VkCommandPool commandPool;
    private VkImage colorImage;
    private VmaAllocation colorImageAllocation;
    private VkImageView colorImageView;
    private @enumtype(VkFormat.class) int depthFormat;
    private VkImage depthImage;
    private VmaAllocation depthImageAllocation;
    private VkImageView depthImageView;
    private int textureMipLevels;
    private VkImage textureImage;
    private VmaAllocation textureImageAllocation;
    private float[] vertices;
    private int[] indices;
    private VkBuffer vertexBuffer;
    private VkImageView textureImageView;
    private VkSampler textureSampler;
    private VmaAllocation vertexBufferAllocation;
    private VkBuffer indexBuffer;
    private VmaAllocation indexBufferAllocation;
    private VkBuffer[] uniformBuffers;
    private VmaAllocation[] uniformBuffersAllocation;
    private FloatBuffer[] uniformBuffersMapped;
    private VkDescriptorPool descriptorPool;
    private VkDescriptorSet[] descriptorSets;
    private VkCommandBuffer[] commandBuffers;
    private VkSemaphore[] imageAvailableSemaphores;
    private VkSemaphore[] renderFinishedSemaphores;
    private VkFence[] inFlightFences;
    private int currentFrame = 0;
    private boolean framebufferResized = false;

    public void run() {
        initWindow();
        initVulkan();
        mainLoop();
        cleanup();
    }

    private void initWindow() {
        GLFWLoader.loadGLFWLibrary();
        glfw = GLFWLoader.loadGLFW();
        if (glfw.glfwInit() != GLFWConstants.GLFW_TRUE) {
            throw new RuntimeException("Failed to initialize GLFW");
        }

        if (glfw.glfwVulkanSupported() != GLFWConstants.GLFW_TRUE) {
            throw new RuntimeException("Vulkan is not supported");
        }

        glfw.glfwWindowHint(GLFWConstants.GLFW_CLIENT_API, GLFWConstants.GLFW_NO_API);
        window = glfw.glfwCreateWindow(WIDTH, HEIGHT, WINDOW_TITLE, null, null);

        var callbackDescriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS,
                ValueLayout.JAVA_INT,
                ValueLayout.JAVA_INT
        );
        try {
            var handle = MethodHandles.lookup().findVirtual(
                    Application.class,
                    "framebufferResizeCallback",
                    callbackDescriptor.toMethodType()
            ).bindTo(this);

            var upcallStub = Linker.nativeLinker().upcallStub(handle, callbackDescriptor, applicationArena);
            glfw.glfwSetFramebufferSizeCallback(window, upcallStub);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find method handle for framebufferResizeCallback", e);
        }
    }

    private void initVulkan() {
        VulkanLoader.loadVulkanLibrary();
        staticCommands = VulkanLoader.loadStaticCommands();
        entryCommands = VulkanLoader.loadEntryCommands();

        createInstance();
        setupDebugMessenger();
        createSurface();
        pickPhysicalDevice();
        createLogicalDevice();
        createVMA();
        createSwapchain();
        createImageViews();
        createDescriptorSetLayout();
        createGraphicsPipeline();
        createCommandPool();
        createColorResources();
        createDepthResources();
        createTextureImage();
        createTextureImageView();
        createTextureSampler();
        loadModel();
        createVertexBuffer();
        createIndexBuffer();
        createUniformBuffers();
        createDescriptorPool();
        createDescriptorSets();
        createCommandBuffers();
        createSyncObjects();
    }

    private void mainLoop() {
        while (glfw.glfwWindowShouldClose(window) == GLFWConstants.GLFW_FALSE) {
            glfw.glfwPollEvents();
            drawFrame();
        }
        deviceCommands.vkDeviceWaitIdle(device);
    }

    private void cleanup() {
        for (int i = 0; i < MAX_FRAMES_IN_FLIGHT; i++) {
            deviceCommands.vkDestroySemaphore(device, renderFinishedSemaphores[i], null);
            deviceCommands.vkDestroySemaphore(device, imageAvailableSemaphores[i], null);
            deviceCommands.vkDestroyFence(device, inFlightFences[i], null);
        }
        deviceCommands.vkDestroyCommandPool(device, commandPool, null);
        cleanupSwapChain();
        deviceCommands.vkDestroySampler(device, textureSampler, null);
        deviceCommands.vkDestroyImageView(device, textureImageView, null);
        vma.vmaDestroyImage(vmaAllocator, textureImage, textureImageAllocation);
        vma.vmaDestroyBuffer(vmaAllocator, vertexBuffer, vertexBufferAllocation);
        vma.vmaDestroyBuffer(vmaAllocator, indexBuffer, indexBufferAllocation);
        deviceCommands.vkDestroyPipeline(device, graphicsPipeline, null);
        deviceCommands.vkDestroyPipelineLayout(device, pipelineLayout, null);
        for (int i = 0; i < MAX_FRAMES_IN_FLIGHT; i++) {
            vma.vmaDestroyBuffer(vmaAllocator, uniformBuffers[i], uniformBuffersAllocation[i]);
        }
        deviceCommands.vkDestroyDescriptorPool(device, descriptorPool, null);
        deviceCommands.vkDestroyDescriptorSetLayout(device, descriptorSetLayout, null);
        vma.vmaDestroyAllocator(vmaAllocator);
        deviceCommands.vkDestroyDevice(device, null);
        if (ENABLE_VALIDATION_LAYERS) {
            instanceCommands.vkDestroyDebugUtilsMessengerEXT(instance, debugMessenger, null);
        }
        instanceCommands.vkDestroySurfaceKHR(instance, surface, null);
        instanceCommands.vkDestroyInstance(instance, null);
        glfw.glfwDestroyWindow(window);
        glfw.glfwTerminate();
    }

    private void createInstance() {
        if (ENABLE_VALIDATION_LAYERS && !checkValidationLayerSupport()) {
            throw new RuntimeException("Validation layers requested, but not available!");
        }

        try (var arena = Arena.ofConfined()) {
            var appInfo = VkApplicationInfo.allocate(arena);
            appInfo.pApplicationName(ByteBuffer.allocateString(arena, "Zdravstvuyte, Vulkan!"));
            appInfo.applicationVersion(Version.vkMakeAPIVersion(0, 1, 0, 0));
            appInfo.pEngineName(ByteBuffer.allocateString(arena, "Soloviev D-30"));
            appInfo.engineVersion(Version.vkMakeAPIVersion(0, 1, 0, 0));
            appInfo.apiVersion(Version.VK_API_VERSION_1_3);

            var instanceCreateInfo = VkInstanceCreateInfo.allocate(arena);
            instanceCreateInfo.pApplicationInfo(appInfo);

            var extensions = getRequiredExtensions(arena);
            instanceCreateInfo.enabledExtensionCount((int) extensions.size());
            instanceCreateInfo.ppEnabledExtensionNames(extensions);

            if (ENABLE_VALIDATION_LAYERS) {
                instanceCreateInfo.enabledLayerCount(1);
                PointerBuffer ppEnabledLayerNames = PointerBuffer.allocate(arena);
                ppEnabledLayerNames.write(ByteBuffer.allocateString(arena, VALIDATION_LAYER_NAME));
                instanceCreateInfo.ppEnabledLayerNames(ppEnabledLayerNames);

                var debugCreateInfo = VkDebugUtilsMessengerCreateInfoEXT.allocate(arena);
                populateDebugMessengerCreateInfo(debugCreateInfo);
                instanceCreateInfo.pNext(debugCreateInfo);
            }

            var pInstance = VkInstance.Buffer.allocate(arena);
            var result = entryCommands.vkCreateInstance(instanceCreateInfo, null, pInstance);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create instance, vulkan error code: " + VkResult.explain(result));
            }
            instance = pInstance.read();
            instanceCommands = VulkanLoader.loadInstanceCommands(instance, staticCommands);

            // optional. show available instance extensions
            IntBuffer pExtensionCount = IntBuffer.allocate(arena);
            result = entryCommands.vkEnumerateInstanceExtensionProperties(null, pExtensionCount, null);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to enumerate instance extension properties, vulkan error code: " + VkResult.explain(result));
            }
            var extensionCount = pExtensionCount.read();

            var extensionProps = VkExtensionProperties.allocate(arena, extensionCount);
            result = entryCommands.vkEnumerateInstanceExtensionProperties(null, pExtensionCount, extensionProps[0]);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to enumerate instance extension properties, vulkan error code: " + VkResult.explain(result));
            }

            System.out.println("instance extensions:");
            for (var extension : extensionProps) {
                System.out.println("\t" + extension.extensionName().readString());
            }
        }
    }

    private static /* VkBool32 */ int debugCallback(
            @enumtype(VkDebugUtilsMessageSeverityFlagsEXT.class) int messageSeverity,
            @enumtype(VkDebugUtilsMessageTypeFlagsEXT.class) int messageType,
            @pointer(comment="const VkDebugUtilsMessengerCallbackDataEXT*") MemorySegment pCallbackData,
            @pointer(comment="void*") MemorySegment pUserData
    ) {
        var callbackData = new VkDebugUtilsMessengerCallbackDataEXT(pCallbackData.reinterpret(VkDebugUtilsMessengerCallbackDataEXT.SIZE));
        System.err.println("Validation layer: " + Objects.requireNonNull(callbackData.pMessage()).readString());
        return Constants.VK_FALSE;
    }

    private void setupDebugMessenger() {
        if (!ENABLE_VALIDATION_LAYERS) {
            return;
        }

        try (var arena = Arena.ofConfined()) {
            var debugUtilsMessengerCreateInfo = VkDebugUtilsMessengerCreateInfoEXT.allocate(arena);
            populateDebugMessengerCreateInfo(debugUtilsMessengerCreateInfo);

            var pDebugMessenger = VkDebugUtilsMessengerEXT.Buffer.allocate(arena);
            var result = instanceCommands.vkCreateDebugUtilsMessengerEXT(
                    instance,
                    debugUtilsMessengerCreateInfo,
                    null,
                    pDebugMessenger
            );
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to set up debug messenger, vulkan error code: " + VkResult.explain(result));
            }
            debugMessenger = pDebugMessenger.read();
        }
    }

    private boolean checkValidationLayerSupport() {
        try (var arena = Arena.ofConfined()) {
            var pLayerCount = IntBuffer.allocate(arena);
            var result = entryCommands.vkEnumerateInstanceLayerProperties(pLayerCount, null);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to enumerate instance layer properties, vulkan error code: " + VkResult.explain(result));
            }

            var layerCount = pLayerCount.read();
            var availableLayers = VkLayerProperties.allocate(arena, layerCount);
            result = entryCommands.vkEnumerateInstanceLayerProperties(pLayerCount, availableLayers[0]);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to enumerate instance layer properties, vulkan error code: " + VkResult.explain(result));
            }

            for (var layer : availableLayers) {
                if (VALIDATION_LAYER_NAME.equals(layer.layerName().readString())) {
                    return true;
                }
            }

            return false;
        }
    }

    private PointerBuffer getRequiredExtensions(Arena arena) {
        try (var localArena = Arena.ofConfined()) {
            var pGLFWExtensionCount = IntBuffer.allocate(localArena);
            var glfwExtensions = glfw.glfwGetRequiredInstanceExtensions(pGLFWExtensionCount);
            if (glfwExtensions == null) {
                throw new RuntimeException("Failed to get GLFW required instance extensions");
            }

            var glfwExtensionCount = pGLFWExtensionCount.read();
            glfwExtensions = glfwExtensions.reinterpret(glfwExtensionCount);
            if (!ENABLE_VALIDATION_LAYERS) {
                return glfwExtensions;
            }
            else {
                var extensions = PointerBuffer.allocate(arena, glfwExtensionCount + 1);
                for (int i = 0; i < glfwExtensionCount; i++) {
                    extensions.write(i, glfwExtensions.read(i));
                }

                extensions.write(glfwExtensionCount, ByteBuffer.allocateString(arena, Constants.VK_EXT_DEBUG_UTILS_EXTENSION_NAME));
                return extensions;
            }
        }
    }

    private void populateDebugMessengerCreateInfo(VkDebugUtilsMessengerCreateInfoEXT debugUtilsMessengerCreateInfo) {
        debugUtilsMessengerCreateInfo.messageSeverity(
                VkDebugUtilsMessageSeverityFlagsEXT.VK_DEBUG_UTILS_MESSAGE_SEVERITY_VERBOSE_BIT_EXT |
                        VkDebugUtilsMessageSeverityFlagsEXT.VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT |
                        VkDebugUtilsMessageSeverityFlagsEXT.VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT
        );
        debugUtilsMessengerCreateInfo.messageType(
                VkDebugUtilsMessageTypeFlagsEXT.VK_DEBUG_UTILS_MESSAGE_TYPE_GENERAL_BIT_EXT |
                        VkDebugUtilsMessageTypeFlagsEXT.VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT |
                        VkDebugUtilsMessageTypeFlagsEXT.VK_DEBUG_UTILS_MESSAGE_TYPE_PERFORMANCE_BIT_EXT
        );
        debugUtilsMessengerCreateInfo.pfnUserCallback(UPCALL_debugCallback);
    }

    private void pickPhysicalDevice() {
        try (var arena = Arena.ofConfined()) {
            var pDeviceCount = IntBuffer.allocate(arena);
            var result = instanceCommands.vkEnumeratePhysicalDevices(instance, pDeviceCount, null);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to enumerate physical devices, vulkan error code: " + VkResult.explain(result));
            }

            var deviceCount = pDeviceCount.read();
            if (deviceCount == 0) {
                throw new RuntimeException("Failed to find GPUs with Vulkan support");
            }

            var pDevices = VkPhysicalDevice.Buffer.allocate(arena, deviceCount);
            result = instanceCommands.vkEnumeratePhysicalDevices(instance, pDeviceCount, pDevices);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to enumerate physical devices, vulkan error code: " + VkResult.explain(result));
            }
            var devices = pDevices.readAll();

            for (var device : devices) {
                if (isDeviceSuitable(device)) {
                    physicalDevice = device;
                    msaaSamples = getMaxUsableSampleCount();
                    break;
                }
            }

            if (physicalDevice == null) {
                throw new RuntimeException("Failed to find a suitable GPU");
            }

            // optional. show available device extensions
            System.out.println("device extensions:");
            for (var extension : getAvailableDeviceExtensionProperties(physicalDevice, arena)) {
                System.out.println("\t" + extension.extensionName().readString());
            }
        }
    }

    private boolean isDeviceSuitable(VkPhysicalDevice device) {
//        try (var arena = Arena.ofConfined()) {
//            var properties = VkPhysicalDeviceProperties.allocate(arena);
//            instanceCommands.vkGetPhysicalDeviceProperties(device, properties);
//
//            var features = VkPhysicalDeviceFeatures.allocate(arena);
//            instanceCommands.vkGetPhysicalDeviceFeatures(device, features);
//
//            return properties.deviceType() == VkPhysicalDeviceType.VK_PHYSICAL_DEVICE_TYPE_DISCRETE_GPU &&
//                    features.geometryShader() == Constants.VK_TRUE && findQueueFamilies(device) != null;
//        }
        var indices = findQueueFamilies(device);
        var extensionsSupported = checkDeviceExtensionSupport(device);
        if ((indices == null) || !extensionsSupported) {
            return false;
        }

        try (var arena = Arena.ofConfined()) {
            var swapChainSupport = querySwapChainSupport(device, arena);
            // Swap chain support is sufficient for this tutorial if there is at least one supported image format and one supported presentation mode given the window surface we have
            var supportedFeatures = VkPhysicalDeviceFeatures.allocate(arena);
            instanceCommands.vkGetPhysicalDeviceFeatures(device, supportedFeatures);
            return swapChainSupport.formats().length != 0
                    && swapChainSupport.presentModes().size() != 0
                    && supportedFeatures.samplerAnisotropy() == Constants.VK_TRUE;
        }
    }

    private record QueueFamilyIndices(int graphicsFamily, int presentFamily) {}
    private record SwapchainSupportDetails(
            VkSurfaceCapabilitiesKHR capabilities,
            VkSurfaceFormatKHR[] formats,
            @enumtype(VkPresentModeKHR.class) IntBuffer presentModes
    ) {}

    private QueueFamilyIndices findQueueFamilies(VkPhysicalDevice device) {
        // Logic to find queue family indices to create record with
        try (var arena = Arena.ofConfined()) {
            var pQueueFamilyCount = IntBuffer.allocate(arena);
            instanceCommands.vkGetPhysicalDeviceQueueFamilyProperties(device, pQueueFamilyCount, null);

            var queueFamilyCount = pQueueFamilyCount.read();
            var queueFamilies = VkQueueFamilyProperties.allocate(arena, queueFamilyCount);
            instanceCommands.vkGetPhysicalDeviceQueueFamilyProperties(device, pQueueFamilyCount, queueFamilies[0]);

            Integer graphicsFamily = null;
            Integer presentFamily = null;
            var pSurfaceSupport = IntBuffer.allocate(arena);
            for (int i = 0; i < queueFamilyCount; i++) {
                //  We need to find at least one queue family that supports VK_QUEUE_GRAPHICS_BIT
                if (queueFamilies[i].queueCount() > 0 && (queueFamilies[i].queueFlags() & VkQueueFlags.VK_QUEUE_GRAPHICS_BIT) != 0) {
                    graphicsFamily = i;
                }

                instanceCommands.vkGetPhysicalDeviceSurfaceSupportKHR(device, i, surface, pSurfaceSupport);
                if (pSurfaceSupport.read() == Constants.VK_TRUE) {
                    presentFamily = i;
                }

                if (graphicsFamily != null && presentFamily != null) {
                    break;
                }
            }

            if (graphicsFamily != null && presentFamily != null) {
                return new QueueFamilyIndices(graphicsFamily, presentFamily);
            }
            else {
                return null;
            }
        }
    }

    private void createLogicalDevice() {
        var indices = findQueueFamilies(physicalDevice);
        assert indices != null;

        try (var arena = Arena.ofConfined()) {
            var deviceFeatures = VkPhysicalDeviceFeatures.allocate(arena);
            deviceFeatures.samplerAnisotropy(Constants.VK_TRUE);
            deviceFeatures.sampleRateShading(Constants.VK_TRUE);

            var deviceCreateInfo = VkDeviceCreateInfo.allocate(arena);
            var pQueuePriorities = FloatBuffer.allocate(arena);
            pQueuePriorities.write(1.0f);
            if (indices.graphicsFamily == indices.presentFamily) {
                var queueCreateInfo = VkDeviceQueueCreateInfo.allocate(arena);
                queueCreateInfo.queueCount(1);
                queueCreateInfo.queueFamilyIndex(indices.graphicsFamily());
                queueCreateInfo.pQueuePriorities(pQueuePriorities);
                deviceCreateInfo.queueCreateInfoCount(1);
                deviceCreateInfo.pQueueCreateInfos(queueCreateInfo);
            }
            else {
                var queueCreateInfos = VkDeviceQueueCreateInfo.allocate(arena, 2);
                queueCreateInfos[0].queueCount(1);
                queueCreateInfos[0].queueFamilyIndex(indices.graphicsFamily());
                queueCreateInfos[0].pQueuePriorities(pQueuePriorities);
                queueCreateInfos[1].queueCount(1);
                queueCreateInfos[1].queueFamilyIndex(indices.presentFamily());
                queueCreateInfos[1].pQueuePriorities(pQueuePriorities);
                deviceCreateInfo.queueCreateInfoCount(2);
                deviceCreateInfo.pQueueCreateInfos(queueCreateInfos[0]);
            }
            deviceCreateInfo.pEnabledFeatures(deviceFeatures);

            // Enabling device extensions (VK_KHR_swapchain )
            deviceCreateInfo.enabledExtensionCount(1);
            var ppDeviceExtensions = PointerBuffer.allocate(arena);
            ppDeviceExtensions.write(ByteBuffer.allocateString(arena, Constants.VK_KHR_SWAPCHAIN_EXTENSION_NAME));
            deviceCreateInfo.ppEnabledExtensionNames(ppDeviceExtensions);

            if (ENABLE_VALIDATION_LAYERS) {
                deviceCreateInfo.enabledLayerCount(1);
                PointerBuffer ppEnabledLayerNames = PointerBuffer.allocate(arena);
                ppEnabledLayerNames.write(ByteBuffer.allocateString(arena, VALIDATION_LAYER_NAME));
                deviceCreateInfo.ppEnabledLayerNames(ppEnabledLayerNames);
            }

            var dynamicRenderingFeature = VkPhysicalDeviceDynamicRenderingFeatures.allocate(arena);
            dynamicRenderingFeature.dynamicRendering(Constants.VK_TRUE);
            deviceCreateInfo.pNext(dynamicRenderingFeature);

            var pDevice = VkDevice.Buffer.allocate(arena);
            var result = instanceCommands.vkCreateDevice(physicalDevice, deviceCreateInfo, null, pDevice);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create logical device, vulkan error code: " + VkResult.explain(result));
            }
            device = pDevice.read();
            deviceCommands = VulkanLoader.loadDeviceCommands(instance, device, staticCommands);

            var pQueue = VkQueue.Buffer.allocate(arena);
            deviceCommands.vkGetDeviceQueue(device, indices.graphicsFamily(), 0, pQueue);
            graphicsQueue = pQueue.read();

            deviceCommands.vkGetDeviceQueue(device, indices.presentFamily(), 0, pQueue);
            presentQueue = pQueue.read();
        }
    }

    private void createSurface() {
        try (var arena = Arena.ofConfined()) {
            var pSurface = VkSurfaceKHR.Buffer.allocate(arena);
            var result = glfw.glfwCreateWindowSurface(instance, window, null, pSurface);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create window surface, vulkan error code: " + VkResult.explain(result));
            }
            surface = pSurface.read();
        }
    }

    boolean checkDeviceExtensionSupport(VkPhysicalDevice device) {
        try (var arena = Arena.ofConfined()) {
            for (var extension : getAvailableDeviceExtensionProperties(device, arena)) {
                if (Constants.VK_KHR_SWAPCHAIN_EXTENSION_NAME.equals(extension.extensionName().readString())) {
                    return true;
                }
            }
            return false;
        }
    }

    private VkExtensionProperties[] getAvailableDeviceExtensionProperties(VkPhysicalDevice device, Arena arena) {
        var pExtensionCount = IntBuffer.allocate(arena);
        var result = instanceCommands.vkEnumerateDeviceExtensionProperties(device, null, pExtensionCount, null);
        if (result != VkResult.VK_SUCCESS) {
            throw new RuntimeException("Failed to enumerate device extension properties, vulkan error code: " + VkResult.explain(result));
        }

        var extensionCount = pExtensionCount.read();
        var availableExtensions = VkExtensionProperties.allocate(arena, extensionCount);
        result = instanceCommands.vkEnumerateDeviceExtensionProperties(device, null, pExtensionCount, availableExtensions[0]);
        if (result != VkResult.VK_SUCCESS) {
            throw new RuntimeException("Failed to enumerate device extension properties, vulkan error code: " + VkResult.explain(result));
        }
        return availableExtensions;
    }

    private SwapchainSupportDetails querySwapChainSupport(VkPhysicalDevice device, Arena arena) {
        var surfaceCapabilities = VkSurfaceCapabilitiesKHR.allocate(arena);
        var result = instanceCommands.vkGetPhysicalDeviceSurfaceCapabilitiesKHR(device, surface, surfaceCapabilities);
        if (result != VkResult.VK_SUCCESS) {
            throw new RuntimeException("Failed to get physical device surface capabilities, vulkan error code: " + VkResult.explain(result));
        }

        try (var localArena = Arena.ofConfined()) {
            var pFormatCount = IntBuffer.allocate(localArena);
            result = instanceCommands.vkGetPhysicalDeviceSurfaceFormatsKHR(device, surface, pFormatCount, null);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to get physical device surface formats, vulkan error code: " + VkResult.explain(result));
            }

            var formatCount = pFormatCount.read();
            var formats = VkSurfaceFormatKHR.allocate(arena, formatCount);
            result = instanceCommands.vkGetPhysicalDeviceSurfaceFormatsKHR(device, surface, pFormatCount, formats[0]);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to get physical device surface formats, vulkan error code: " + VkResult.explain(result));
            }

            var pPresentModeCount = IntBuffer.allocate(localArena);
            result = instanceCommands.vkGetPhysicalDeviceSurfacePresentModesKHR(device, surface, pPresentModeCount, null);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to get physical device surface present modes, vulkan error code: " + VkResult.explain(result));
            }

            var presentModeCount = pPresentModeCount.read();
            var presentModes = IntBuffer.allocate(arena, presentModeCount);
            result = instanceCommands.vkGetPhysicalDeviceSurfacePresentModesKHR(device, surface, pPresentModeCount, presentModes);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to get physical device surface present modes, vulkan error code: " + VkResult.explain(result));
            }

            return new SwapchainSupportDetails(surfaceCapabilities, formats, presentModes);
        }
    }

    /*
     To find the right settings for the best possible swap chain. There are three types of settings to determine:
     - Surface format (color depth)
     - Presentation mode (conditions for "swapping" images to the screen)
     - Swap extent (resolution of images in swap chain)
     */
    private VkSurfaceFormatKHR chooseSwapSurfaceFormat(VkSurfaceFormatKHR[] formats) {
        for (var format : formats) {
            if (format.format() == VkFormat.VK_FORMAT_B8G8R8A8_SRGB &&
                    format.colorSpace() == VkColorSpaceKHR.VK_COLOR_SPACE_SRGB_NONLINEAR_KHR) {
                return format;
            }
        }
        return formats[0];
    }

    private @enumtype(VkPresentModeKHR.class) int chooseSwapPresentMode(
            @enumtype(VkPresentModeKHR.class) IntBuffer presentModes
    ) {
        for (int i = 0; i < presentModes.size(); i++) {
            if (presentModes.read(i) == VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR) {
                return VkPresentModeKHR.VK_PRESENT_MODE_MAILBOX_KHR;
            }
        }

        return VkPresentModeKHR.VK_PRESENT_MODE_FIFO_KHR;
    }

    // The swap extent is the resolution of the swap chain images
    private VkExtent2D chooseSwapExtent(VkSurfaceCapabilitiesKHR capabilities, Arena arena) {
        if (capabilities.currentExtent().width() != NativeLayout.UINT32_MAX) {
            return capabilities.currentExtent();
        }
        else {
            try (var localArena = Arena.ofConfined()) {
                var pWidth = IntBuffer.allocate(localArena);
                var pHeight = IntBuffer.allocate(localArena);
                glfw.glfwGetFramebufferSize(window, pWidth, pHeight);
                var width = pWidth.read();
                var height = pHeight.read();

                var actualExtent = VkExtent2D.allocate(arena);
                actualExtent.width(Math.clamp(width, capabilities.minImageExtent().width(), capabilities.maxImageExtent().width()));
                actualExtent.height(Math.clamp(height, capabilities.minImageExtent().height(), capabilities.maxImageExtent().height()));
                return actualExtent;
            }
        }
    }

    private void createSwapchain() {
        try (var arena = Arena.ofConfined()) {
            var swapChainSupport = querySwapChainSupport(physicalDevice, arena);

            var surfaceFormat = chooseSwapSurfaceFormat(swapChainSupport.formats());
            var presentMode = chooseSwapPresentMode(swapChainSupport.presentModes());
            var extent = chooseSwapExtent(swapChainSupport.capabilities(), arena);

            // However, simply sticking to this minimum means that we may sometimes have to wait on the driver to complete internal operations before we can acquire another image to render to.
            // Therefore, it is recommended to request at least one more image than the minimum:
            var imageCount = swapChainSupport.capabilities.minImageCount() + 1;

            if (swapChainSupport.capabilities.maxImageCount() > 0
                    && imageCount > swapChainSupport.capabilities.maxImageCount()) {
                imageCount = swapChainSupport.capabilities.maxImageCount();
            }

            var createInfo = VkSwapchainCreateInfoKHR.allocate(arena);
            createInfo.surface(surface);
            createInfo.minImageCount(imageCount);
            createInfo.imageFormat(surfaceFormat.format());
            createInfo.imageColorSpace(surfaceFormat.colorSpace());
            createInfo.imageExtent(extent);
            createInfo.imageArrayLayers(1);
            /*
            The imageArrayLayers specifies the amount of layers each image consists of.
            This is always 1 unless you are developing a stereoscopic 3D application.
            The imageUsage bit field specifies what kind of operations we'll use the images in the swap chain for.
            In this tutorial we're going to render directly to them, which means that they're used as color attachment.
            It is also possible that you'll render images to a separate image first to perform operations like post-processing.
            In that case you may use a value like VK_IMAGE_USAGE_TRANSFER_DST_BIT instead and use a memory operation to transfer the rendered image to a swap chain image.
             */
            createInfo.imageUsage(VkImageUsageFlags.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT);

            var indices = findQueueFamilies(physicalDevice);
            assert indices != null;
            if (indices.graphicsFamily != indices.presentFamily) {
                createInfo.imageSharingMode(VkSharingMode.VK_SHARING_MODE_CONCURRENT);
                createInfo.queueFamilyIndexCount(2);
                var pQueueFamilyIndices = IntBuffer.allocate(arena);
                pQueueFamilyIndices.write(indices.graphicsFamily(), indices.presentFamily());
                createInfo.pQueueFamilyIndices(pQueueFamilyIndices);
            }
            else {
                createInfo.imageSharingMode(VkSharingMode.VK_SHARING_MODE_EXCLUSIVE);
            }

            createInfo.preTransform(swapChainSupport.capabilities.currentTransform());
            createInfo.compositeAlpha(VkCompositeAlphaFlagsKHR.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR);
            createInfo.presentMode(presentMode);
            createInfo.clipped(Constants.VK_TRUE);
            createInfo.oldSwapchain(null); // optional

            var pSwapChain = VkSwapchainKHR.Buffer.allocate(arena);
            var result = deviceCommands.vkCreateSwapchainKHR(device, createInfo, null, pSwapChain);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create swap chain, vulkan error code: " + VkResult.explain(result));
            }
            swapChain = pSwapChain.read();

            var pImageCount = IntBuffer.allocate(arena);
            result = deviceCommands.vkGetSwapchainImagesKHR(device, swapChain, pImageCount, null);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to get swap chain images, vulkan error code: " + VkResult.explain(result));
            }

            imageCount = pImageCount.read();
            var pSwapChainImages = VkImage.Buffer.allocate(arena, imageCount);
            result = deviceCommands.vkGetSwapchainImagesKHR(device, swapChain, pImageCount, pSwapChainImages);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to get swap chain images, vulkan error code: " + VkResult.explain(result));
            }

            swapChainImages = pSwapChainImages.readAll();

            swapChainImageFormat = surfaceFormat.format();
            swapChainExtent = VkExtent2D.clone(applicationArena, extent);
        }
    }

    private void createImageViews() {
        swapChainImageViews = new VkImageView[swapChainImages.length];
        for (int i = 0; i < swapChainImages.length; i++) {
            swapChainImageViews[i] = createImageView(
                    swapChainImages[i],
                    swapChainImageFormat,
                    VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT,
                    1
            );
        }
    }

    private void createGraphicsPipeline() {
        try (var arena = Arena.ofConfined()) {
            var vertShaderCode = readShaderFile("/shader/vert.spv", arena);
            var fragShaderCode = readShaderFile("/shader/frag.spv", arena);

            var vertexShaderModule = createShaderModule(vertShaderCode);
            var fragmentShaderModule = createShaderModule(fragShaderCode);

            var shaderStages = VkPipelineShaderStageCreateInfo.allocate(arena, 2);

            var vertShaderStageInfo = shaderStages[0];
            vertShaderStageInfo.stage(VkShaderStageFlags.VK_SHADER_STAGE_VERTEX_BIT);

            vertShaderStageInfo.module(vertexShaderModule);
            vertShaderStageInfo.pName(ByteBuffer.allocateString(arena, "main"));

            var fragShaderStageInfo = shaderStages[1];
            fragShaderStageInfo.stage(VkShaderStageFlags.VK_SHADER_STAGE_FRAGMENT_BIT);
            fragShaderStageInfo.module(fragmentShaderModule);
            fragShaderStageInfo.pName(ByteBuffer.allocateString(arena, "main"));

            var vertexInputInfo = VkPipelineVertexInputStateCreateInfo.allocate(arena);
            var bindingDescription = getBindingDescription(arena);
            var attributeDescription = getAttributeDescriptions(arena);
            vertexInputInfo.vertexBindingDescriptionCount(1);
            vertexInputInfo.pVertexBindingDescriptions(bindingDescription);
            vertexInputInfo.vertexAttributeDescriptionCount(attributeDescription.length);
            vertexInputInfo.pVertexAttributeDescriptions(attributeDescription[0]);

            var inputAssembly = VkPipelineInputAssemblyStateCreateInfo.allocate(arena);
            inputAssembly.topology(VkPrimitiveTopology.VK_PRIMITIVE_TOPOLOGY_TRIANGLE_LIST);
            inputAssembly.primitiveRestartEnable(Constants.VK_FALSE);

            var dynamicStates = IntBuffer.allocate(arena, 2);
            dynamicStates.write(0, VkDynamicState.VK_DYNAMIC_STATE_VIEWPORT);
            dynamicStates.write(1, VkDynamicState.VK_DYNAMIC_STATE_SCISSOR);
            var dynamicStateInfo = VkPipelineDynamicStateCreateInfo.allocate(arena);
            dynamicStateInfo.dynamicStateCount(2);
            dynamicStateInfo.pDynamicStates(dynamicStates);

            var viewportStateInfo = VkPipelineViewportStateCreateInfo.allocate(arena);
            viewportStateInfo.viewportCount(1);
            viewportStateInfo.scissorCount(1);

            var rasterizer = VkPipelineRasterizationStateCreateInfo.allocate(arena);
            rasterizer.depthClampEnable(Constants.VK_FALSE);
            rasterizer.rasterizerDiscardEnable(Constants.VK_FALSE);
            rasterizer.polygonMode(VkPolygonMode.VK_POLYGON_MODE_FILL);
            rasterizer.lineWidth(1.0f);
            rasterizer.cullMode(VkCullModeFlags.VK_CULL_MODE_BACK_BIT);
            rasterizer.frontFace(VkFrontFace.VK_FRONT_FACE_COUNTER_CLOCKWISE);
            rasterizer.depthBiasEnable(Constants.VK_FALSE);
            rasterizer.depthBiasConstantFactor(0.0f);
            rasterizer.depthBiasClamp(0.0f);
            rasterizer.depthBiasSlopeFactor(0.0f);

            var multisampling = VkPipelineMultisampleStateCreateInfo.allocate(arena);
            multisampling.sampleShadingEnable(Constants.VK_FALSE);
            multisampling.rasterizationSamples(msaaSamples);
            multisampling.minSampleShading(1.0f);
            multisampling.pSampleMask(null);
            multisampling.alphaToCoverageEnable(Constants.VK_FALSE);
            multisampling.alphaToOneEnable(Constants.VK_FALSE);
            multisampling.sampleShadingEnable(Constants.VK_TRUE);
            multisampling.minSampleShading(0.2f);

            var colorBlendAttachment = VkPipelineColorBlendAttachmentState.allocate(arena);
            colorBlendAttachment.colorWriteMask(
                    VkColorComponentFlags.VK_COLOR_COMPONENT_R_BIT |
                            VkColorComponentFlags.VK_COLOR_COMPONENT_G_BIT |
                            VkColorComponentFlags.VK_COLOR_COMPONENT_B_BIT |
                            VkColorComponentFlags.VK_COLOR_COMPONENT_A_BIT
            );
            colorBlendAttachment.blendEnable(Constants.VK_FALSE);
            colorBlendAttachment.srcColorBlendFactor(VkBlendFactor.VK_BLEND_FACTOR_ONE);
            colorBlendAttachment.dstColorBlendFactor(VkBlendFactor.VK_BLEND_FACTOR_ZERO);
            colorBlendAttachment.colorBlendOp(VkBlendOp.VK_BLEND_OP_ADD);
            colorBlendAttachment.srcAlphaBlendFactor(VkBlendFactor.VK_BLEND_FACTOR_ONE);
            colorBlendAttachment.dstAlphaBlendFactor(VkBlendFactor.VK_BLEND_FACTOR_ZERO);
            colorBlendAttachment.alphaBlendOp(VkBlendOp.VK_BLEND_OP_ADD);

            var colorBlending = VkPipelineColorBlendStateCreateInfo.allocate(arena);
            colorBlending.logicOpEnable(Constants.VK_FALSE);
            colorBlending.logicOp(VkLogicOp.VK_LOGIC_OP_COPY);
            colorBlending.attachmentCount(1);
            colorBlending.pAttachments(colorBlendAttachment);
            colorBlending.blendConstants().write(0, 0.0f);
            colorBlending.blendConstants().write(1, 0.0f);
            colorBlending.blendConstants().write(2, 0.0f);
            colorBlending.blendConstants().write(3, 0.0f);

            var depthStencil = VkPipelineDepthStencilStateCreateInfo.allocate(arena);
            depthStencil.depthTestEnable(Constants.VK_TRUE);
            depthStencil.depthWriteEnable(Constants.VK_TRUE);
            depthStencil.depthCompareOp(VkCompareOp.VK_COMPARE_OP_LESS);
            depthStencil.depthBoundsTestEnable(Constants.VK_FALSE);
            depthStencil.minDepthBounds(0.0f);
            depthStencil.maxDepthBounds(1.0f);
            depthStencil.stencilTestEnable(Constants.VK_FALSE);

            var pipelineLayoutInfo = VkPipelineLayoutCreateInfo.allocate(arena);
            pipelineLayoutInfo.pushConstantRangeCount(0);
            pipelineLayoutInfo.pPushConstantRanges(null);
            var pDescriptorSetLayout = VkDescriptorSetLayout.Buffer.allocate(arena);
            pDescriptorSetLayout.write(0, descriptorSetLayout);
            pipelineLayoutInfo.setLayoutCount(1);
            pipelineLayoutInfo.pSetLayouts(pDescriptorSetLayout);

            var pPipelineLayout = VkPipelineLayout.Buffer.allocate(arena);
            var result = deviceCommands.vkCreatePipelineLayout(device, pipelineLayoutInfo, null, pPipelineLayout);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create pipeline layout, vulkan error code: " + VkResult.explain(result));
            }
            pipelineLayout = pPipelineLayout.read();

            var pipelineInfo = VkGraphicsPipelineCreateInfo.allocate(arena);
            pipelineInfo.stageCount(2);
            pipelineInfo.pStages(shaderStages[0]);
            pipelineInfo.pVertexInputState(vertexInputInfo);
            pipelineInfo.pInputAssemblyState(inputAssembly);
            pipelineInfo.pViewportState(viewportStateInfo);
            pipelineInfo.pRasterizationState(rasterizer);
            pipelineInfo.pMultisampleState(multisampling);
            pipelineInfo.pDepthStencilState(depthStencil);
            pipelineInfo.pColorBlendState(colorBlending);
            pipelineInfo.pDynamicState(dynamicStateInfo);
            pipelineInfo.layout(pipelineLayout);
            pipelineInfo.basePipelineHandle(null);
            pipelineInfo.basePipelineIndex(-1);

            var pipelineRenderingCreateInfo = VkPipelineRenderingCreateInfo.allocate(arena);
            pipelineRenderingCreateInfo.colorAttachmentCount(1);
            var pColorAttachmentFormats = IntBuffer.allocate(arena);
            pColorAttachmentFormats.write(swapChainImageFormat);
            pipelineRenderingCreateInfo.pColorAttachmentFormats(pColorAttachmentFormats);
            depthFormat = findDepthFormat();
            pipelineRenderingCreateInfo.depthAttachmentFormat(depthFormat);
            pipelineInfo.pNext(pipelineRenderingCreateInfo);

            var pGraphicsPipeline = VkPipeline.Buffer.allocate(arena);
            result = deviceCommands.vkCreateGraphicsPipelines(device, null, 1, pipelineInfo, null, pGraphicsPipeline);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create graphics pipeline, vulkan error code: " + VkResult.explain(result));
            }
            graphicsPipeline = pGraphicsPipeline.read();

            deviceCommands.vkDestroyShaderModule(device, vertexShaderModule, null);
            deviceCommands.vkDestroyShaderModule(device, fragmentShaderModule, null);
        }
    }

    private void createCommandPool() {
        try (var arena = Arena.ofConfined()) {
            var queueFamilyIndices = findQueueFamilies(physicalDevice);
            assert queueFamilyIndices != null;

            var poolInfo = VkCommandPoolCreateInfo.allocate(arena);
            poolInfo.flags(VkCommandPoolCreateFlags.VK_COMMAND_POOL_CREATE_RESET_COMMAND_BUFFER_BIT);
            poolInfo.queueFamilyIndex(queueFamilyIndices.graphicsFamily());

            var pCommandPool = VkCommandPool.Buffer.allocate(arena);
            var result = deviceCommands.vkCreateCommandPool(device, poolInfo, null, pCommandPool);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create command pool, vulkan error code: " + VkResult.explain(result));
            }
            commandPool = pCommandPool.read();
        }
    }

    private void createCommandBuffers() {
        try (var arena = Arena.ofConfined()) {
            var allocInfo = VkCommandBufferAllocateInfo.allocate(arena);
            allocInfo.commandPool(commandPool);
            allocInfo.level(VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY);
            allocInfo.commandBufferCount(MAX_FRAMES_IN_FLIGHT);

            var pCommandBuffers = VkCommandBuffer.Buffer.allocate(arena, MAX_FRAMES_IN_FLIGHT);
            var result = deviceCommands.vkAllocateCommandBuffers(device, allocInfo, pCommandBuffers);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to allocate command buffer, vulkan error code: " + VkResult.explain(result));
            }
            commandBuffers = pCommandBuffers.readAll();
        }
    }

    private static IntBuffer readShaderFile(String filename, Arena arena) {
        try (var stream = Application.class.getResourceAsStream(filename)) {
            if (stream == null) {
                throw new RuntimeException("Failed to open shader file: " + filename);
            }

            var bytes = stream.readAllBytes();
            assert bytes.length % Integer.BYTES == 0;

            return IntBuffer.allocate(arena, bytes);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to read shader file: " + filename, e);
        }
    }

    private VkShaderModule createShaderModule(IntBuffer code) {
        try (var localArena = Arena.ofConfined()) {
            var createInfo = VkShaderModuleCreateInfo.allocate(localArena);
            createInfo.codeSize(code.size() * Integer.BYTES);
            createInfo.pCode(code);

            var pShaderModule = VkShaderModule.Buffer.allocate(localArena);
            var result = deviceCommands.vkCreateShaderModule(device, createInfo, null, pShaderModule);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create shader module, vulkan error code: " + VkResult.explain(result));
            }
            return pShaderModule.read();
        }
    }

    private void recordCommandBuffer(VkCommandBuffer commandBuffer, int imageIndex) {
        try (var arena = Arena.ofConfined()) {
            var beginInfo = VkCommandBufferBeginInfo.allocate(arena);
            beginInfo.flags(0);
            beginInfo.pInheritanceInfo(null);

            var result = deviceCommands.vkBeginCommandBuffer(commandBuffer, beginInfo);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to begin recording command buffer, vulkan error code: " + VkResult.explain(result));
            }

            var preImageMemoryBarrier = VkImageMemoryBarrier.allocate(arena);
            preImageMemoryBarrier.srcAccessMask(0);
            preImageMemoryBarrier.dstAccessMask(VkAccessFlags.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT);
            preImageMemoryBarrier.oldLayout(VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED);
            preImageMemoryBarrier.newLayout(VkImageLayout.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL);
            preImageMemoryBarrier.image(swapChainImages[imageIndex]);
            preImageMemoryBarrier.subresourceRange().aspectMask(VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT);
            preImageMemoryBarrier.subresourceRange().baseMipLevel(0);
            preImageMemoryBarrier.subresourceRange().levelCount(1);
            preImageMemoryBarrier.subresourceRange().baseArrayLayer(0);
            preImageMemoryBarrier.subresourceRange().layerCount(1);
            deviceCommands.vkCmdPipelineBarrier(
                    commandBuffer,
                    VkPipelineStageFlags.VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT,
                    VkPipelineStageFlags.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT,
                    0,
                    0, null,
                    0, null,
                    1, preImageMemoryBarrier
            );

            var renderingInfo = VkRenderingInfo.allocate(arena);
            renderingInfo.renderArea().offset().x(0);
            renderingInfo.renderArea().offset().y(0);
            renderingInfo.renderArea().extent(swapChainExtent);
            renderingInfo.layerCount(1);
            var renderingAttachmentInfos = VkRenderingAttachmentInfo.allocate(arena, 2);
            var colorAttachmentInfo = renderingAttachmentInfos[0];
            colorAttachmentInfo.imageView(colorImageView);
            colorAttachmentInfo.imageLayout(VkImageLayout.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL);
            colorAttachmentInfo.loadOp(VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_CLEAR);
            colorAttachmentInfo.storeOp(VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE);
            colorAttachmentInfo.resolveMode(VkResolveModeFlags.VK_RESOLVE_MODE_AVERAGE_BIT);
            colorAttachmentInfo.resolveImageView(swapChainImageViews[imageIndex]);
            colorAttachmentInfo.resolveImageLayout(VkImageLayout.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL);
            var depthAttachmentInfo = renderingAttachmentInfos[1];
            depthAttachmentInfo.imageView(depthImageView);
            depthAttachmentInfo.imageLayout(VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL);
            depthAttachmentInfo.loadOp(VkAttachmentLoadOp.VK_ATTACHMENT_LOAD_OP_CLEAR);
            depthAttachmentInfo.storeOp(VkAttachmentStoreOp.VK_ATTACHMENT_STORE_OP_DONT_CARE);
            depthAttachmentInfo.clearValue().depthStencil().depth(1.0f);

            renderingInfo.colorAttachmentCount(1);
            renderingInfo.pColorAttachments(colorAttachmentInfo);
            renderingInfo.pDepthAttachment(depthAttachmentInfo);

            deviceCommands.vkCmdBeginRendering(commandBuffer, renderingInfo);
            deviceCommands.vkCmdBindPipeline(
                    commandBuffer,
                    VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS,
                    graphicsPipeline
            );
//            deviceCommands.vkCmdBindPipeline(
//                    commandBuffer,
//                    VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS,
//                    graphicsPipeline
//            );

            var viewport = VkViewport.allocate(arena);
            viewport.x(0.0f);
            viewport.y(swapChainExtent.height());
            viewport.width(swapChainExtent.width());
            viewport.height(-swapChainExtent.height());
            viewport.minDepth(0.0f);
            viewport.maxDepth(1.0f);
            deviceCommands.vkCmdSetViewport(commandBuffer, 0, 1, viewport);

            var scissor = VkRect2D.allocate(arena);
            scissor.offset().x(0);
            scissor.offset().y(0);
            scissor.extent(swapChainExtent);
            deviceCommands.vkCmdSetScissor(commandBuffer, 0, 1, scissor);

            var vertexBuffers = VkBuffer.Buffer.allocate(arena);
            vertexBuffers.write(vertexBuffer);
            var offsets = LongBuffer.allocate(arena);
            offsets.write(0);
            deviceCommands.vkCmdBindVertexBuffers(commandBuffer, 0, 1, vertexBuffers, offsets);
            deviceCommands.vkCmdBindIndexBuffer(commandBuffer, indexBuffer, 0, VkIndexType.VK_INDEX_TYPE_UINT32);

            var pDescriptorSet = VkDescriptorSet.Buffer.allocate(arena);
            pDescriptorSet.write(descriptorSets[currentFrame]);
            deviceCommands.vkCmdBindDescriptorSets(
                    commandBuffer,
                    VkPipelineBindPoint.VK_PIPELINE_BIND_POINT_GRAPHICS,
                    pipelineLayout,
                    0,
                    1,
                    pDescriptorSet,
                    0,
                    null
            );

            deviceCommands.vkCmdDrawIndexed(commandBuffer, indices.length, 1, 0, 0, 0);
            deviceCommands.vkCmdEndRendering(commandBuffer);

            var postImageMemoryBarrier = VkImageMemoryBarrier.allocate(arena);
            postImageMemoryBarrier.srcAccessMask(VkAccessFlags.VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT);
            postImageMemoryBarrier.dstAccessMask(0);
            postImageMemoryBarrier.oldLayout(VkImageLayout.VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL);
            postImageMemoryBarrier.newLayout(VkImageLayout.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR);
            postImageMemoryBarrier.image(swapChainImages[imageIndex]);
            postImageMemoryBarrier.subresourceRange().aspectMask(VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT);
            postImageMemoryBarrier.subresourceRange().baseMipLevel(0);
            postImageMemoryBarrier.subresourceRange().levelCount(1);
            postImageMemoryBarrier.subresourceRange().baseArrayLayer(0);
            postImageMemoryBarrier.subresourceRange().layerCount(1);
            deviceCommands.vkCmdPipelineBarrier(
                    commandBuffer,
                    VkPipelineStageFlags.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT,
                    VkPipelineStageFlags.VK_PIPELINE_STAGE_BOTTOM_OF_PIPE_BIT,
                    0,
                    0, null,
                    0, null,
                    1, postImageMemoryBarrier
            );

            result = deviceCommands.vkEndCommandBuffer(commandBuffer);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to end recording command buffer, vulkan error code: " + VkResult.explain(result));
            }
        }
    }

    private void createSyncObjects() {
        try (var arena = Arena.ofConfined()) {
            var semaphoreInfo = VkSemaphoreCreateInfo.allocate(arena);
            var fenceCreateInfo = VkFenceCreateInfo.allocate(arena);
            fenceCreateInfo.flags(VkFenceCreateFlags.VK_FENCE_CREATE_SIGNALED_BIT);

            var pImageAvailableSemaphore = VkSemaphore.Buffer.allocate(arena);
            var pRenderFinishedSemaphore = VkSemaphore.Buffer.allocate(arena);
            var pInFlightFence = VkFence.Buffer.allocate(arena);

            imageAvailableSemaphores = new VkSemaphore[MAX_FRAMES_IN_FLIGHT];
            renderFinishedSemaphores = new VkSemaphore[MAX_FRAMES_IN_FLIGHT];
            inFlightFences = new VkFence[MAX_FRAMES_IN_FLIGHT];

            for (int i = 0; i < MAX_FRAMES_IN_FLIGHT; i++) {
                if (deviceCommands.vkCreateSemaphore(device, semaphoreInfo, null, pImageAvailableSemaphore) != VkResult.VK_SUCCESS ||
                        deviceCommands.vkCreateSemaphore(device, semaphoreInfo, null, pRenderFinishedSemaphore) != VkResult.VK_SUCCESS ||
                        deviceCommands.vkCreateFence(device, fenceCreateInfo, null, pInFlightFence) != VkResult.VK_SUCCESS) {
                    throw new RuntimeException("Failed to create synchronization objects");
                }

                imageAvailableSemaphores[i] = pImageAvailableSemaphore.read();
                renderFinishedSemaphores[i] = pRenderFinishedSemaphore.read();
                inFlightFences[i] = pInFlightFence.read();
            }
        }
    }

    private void drawFrame() {
        var inFlightFence = inFlightFences[currentFrame];
        var imageAvailableSemaphore = imageAvailableSemaphores[currentFrame];
        var renderFinishedSemaphore = renderFinishedSemaphores[currentFrame];
        var commandBuffer = commandBuffers[currentFrame];

        try (var arena = Arena.ofConfined()) {
            var pInFlightFences = VkFence.Buffer.allocate(arena);
            pInFlightFences.write(inFlightFence);
            deviceCommands.vkWaitForFences(device, 1, pInFlightFences, Constants.VK_TRUE, NativeLayout.UINT64_MAX);

            var pImageIndex = IntBuffer.allocate(arena);
            var result = deviceCommands.vkAcquireNextImageKHR(
                    device,
                    swapChain,
                    NativeLayout.UINT64_MAX,
                    imageAvailableSemaphore,
                    null,
                    pImageIndex
            );
            if (result == VkResult.VK_ERROR_OUT_OF_DATE_KHR) {
                recreateSwapChain();
                return;
            }
            else if (result != VkResult.VK_SUCCESS && result != VkResult.VK_SUBOPTIMAL_KHR) {
                throw new RuntimeException("Failed to acquire swap chain image, vulkan error code: " + VkResult.explain(result));
            }

            deviceCommands.vkResetFences(device, 1, pInFlightFences);
            var imageIndex = pImageIndex.read();

            deviceCommands.vkResetCommandBuffer(commandBuffer, 0);
            recordCommandBuffer(commandBuffer, imageIndex);

            updateUniformBuffer();

            var submitInfo = VkSubmitInfo.allocate(arena);
            var pWaitSemaphores = VkSemaphore.Buffer.allocate(arena);
            pWaitSemaphores.write(imageAvailableSemaphore);
            var pWaitStages = IntBuffer.allocate(arena);
            pWaitStages.write(VkPipelineStageFlags.VK_PIPELINE_STAGE_COLOR_ATTACHMENT_OUTPUT_BIT);
            submitInfo.waitSemaphoreCount(1);
            submitInfo.pWaitSemaphores(pWaitSemaphores);
            submitInfo.pWaitDstStageMask(pWaitStages);
            var pCommandBuffers = VkCommandBuffer.Buffer.allocate(arena);
            pCommandBuffers.write(commandBuffer);
            submitInfo.commandBufferCount(1);
            submitInfo.pCommandBuffers(pCommandBuffers);
            var pSignalSemaphores = VkSemaphore.Buffer.allocate(arena);
            pSignalSemaphores.write(renderFinishedSemaphore);
            submitInfo.signalSemaphoreCount(1);
            submitInfo.pSignalSemaphores(pSignalSemaphores);

            result = deviceCommands.vkQueueSubmit(graphicsQueue, 1, submitInfo, inFlightFence);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to submit draw command buffer, vulkan error code: " + VkResult.explain(result));
            }

            var presentInfo = VkPresentInfoKHR.allocate(arena);
            presentInfo.waitSemaphoreCount(1);
            presentInfo.pWaitSemaphores(pSignalSemaphores);

            var swapChains = VkSwapchainKHR.Buffer.allocate(arena);
            swapChains.write(swapChain);
            presentInfo.swapchainCount(1);
            presentInfo.pSwapchains(swapChains);
            presentInfo.pImageIndices(pImageIndex);
            presentInfo.pResults(null);

            result = deviceCommands.vkQueuePresentKHR(presentQueue, presentInfo);
            if (result == VkResult.VK_ERROR_OUT_OF_DATE_KHR || framebufferResized) {
                framebufferResized = false;
                recreateSwapChain();
            }
            else if (result != VkResult.VK_SUCCESS && result != VkResult.VK_SUBOPTIMAL_KHR) {
                throw new RuntimeException("Failed to submit draw command buffer, vulkan error code: " + VkResult.explain(result));
            }
        }

        currentFrame = (currentFrame + 1) % MAX_FRAMES_IN_FLIGHT;
    }

    private void framebufferResizeCallback(
            @pointer(comment = "GLFWwindow*") MemorySegment window,
            int width,
            int height
    ) {
        framebufferResized = true;
    }

    private void recreateSwapChain() {
        try (var arena = Arena.ofConfined()) {
            var pWidth = IntBuffer.allocate(arena);
            var pHeight = IntBuffer.allocate(arena);
            glfw.glfwGetFramebufferSize(window, pWidth, pHeight);
            while (pWidth.read() == 0 || pHeight.read() == 0) {
                glfw.glfwGetFramebufferSize(window, pWidth, pHeight);
                glfw.glfwWaitEvents();
            }
        }

        deviceCommands.vkDeviceWaitIdle(device);

        cleanupSwapChain();

        createSwapchain();
        createImageViews();
        createColorResources();
        createDepthResources();
    }

    private void cleanupSwapChain() {
        deviceCommands.vkDestroyImageView(device, colorImageView, null);
        vma.vmaDestroyImage(vmaAllocator, colorImage, colorImageAllocation);
        deviceCommands.vkDestroyImageView(device, depthImageView, null);
        vma.vmaDestroyImage(vmaAllocator, depthImage, depthImageAllocation);

        for (var imageView : swapChainImageViews) {
            deviceCommands.vkDestroyImageView(device, imageView, null);
        }

        deviceCommands.vkDestroySwapchainKHR(device, swapChain, null);
    }

    private static VkVertexInputBindingDescription getBindingDescription(Arena arena) {
        var description = VkVertexInputBindingDescription.allocate(arena);
        description.binding(0);
        description.stride(Float.BYTES * 8); // 3 floats for position, 3 for color, 2 for texture coordinates
        description.inputRate(VkVertexInputRate.VK_VERTEX_INPUT_RATE_VERTEX);
        return description;
    }

    private static VkVertexInputAttributeDescription[] getAttributeDescriptions(Arena arena) {
        var attributeDescriptions = VkVertexInputAttributeDescription.allocate(arena, 3);

        //pos
        attributeDescriptions[0].binding(0);
        attributeDescriptions[0].location(0);
        attributeDescriptions[0].format(VkFormat.VK_FORMAT_R32G32B32_SFLOAT);
        attributeDescriptions[0].offset(0);

        //color
        attributeDescriptions[1].binding(0);
        attributeDescriptions[1].location(1);
        attributeDescriptions[1].format(VkFormat.VK_FORMAT_R32G32B32_SFLOAT);
        attributeDescriptions[1].offset(Float.BYTES * 3);

        //texture coordinates
        attributeDescriptions[2].binding(0);
        attributeDescriptions[2].location(2);
        attributeDescriptions[2].format(VkFormat.VK_FORMAT_R32G32_SFLOAT);
        attributeDescriptions[2].offset(Float.BYTES * 6);

        return attributeDescriptions;
    }

    private int findMemoryType(int typeFilter, @enumtype(VkMemoryPropertyFlags.class) int properties) {
        try (var arena = Arena.ofConfined()) {
            var memProperties = VkPhysicalDeviceMemoryProperties.allocate(arena);
            instanceCommands.vkGetPhysicalDeviceMemoryProperties(physicalDevice, memProperties);

            for (int i = 0; i < memProperties.memoryTypeCount(); i++) {
                if ((typeFilter & (1 << i)) != 0 &&
                        (memProperties.memoryTypesAt(i).propertyFlags() & properties) == properties) {
                    return i;
                }
            }

            throw new RuntimeException("Failed to find suitable memory type");
        }
    }

    private void createVertexBuffer() {
        try (var arena = Arena.ofConfined()) {
            var bufferSize = vertices.length * Float.BYTES;

            var pair = createBuffer(
                    bufferSize,
                    VkBufferUsageFlags.VK_BUFFER_USAGE_TRANSFER_SRC_BIT,
                    VmaAllocationCreateFlags.VMA_ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT,
                    null
            );
            var stagingBuffer = pair.first;
            var stagingBufferAllocation = pair.second;

            var ppData = PointerBuffer.allocate(arena);
            var result = vma.vmaMapMemory(vmaAllocator, stagingBufferAllocation, ppData);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to map vertex buffer memory, vulkan error code: " + VkResult.explain(result));
            }
            var pData = ppData.read().reinterpret(bufferSize);
            pData.copyFrom(MemorySegment.ofArray(vertices));
            vma.vmaUnmapMemory(vmaAllocator, stagingBufferAllocation);

            pair = createBuffer(
                    bufferSize,
                    VkBufferUsageFlags.VK_BUFFER_USAGE_TRANSFER_DST_BIT | VkBufferUsageFlags.VK_BUFFER_USAGE_VERTEX_BUFFER_BIT,
                    0,
                    null
            );
            vertexBuffer = pair.first;
            vertexBufferAllocation = pair.second;

            copyBuffer(stagingBuffer, vertexBuffer, bufferSize);
            vma.vmaDestroyBuffer(vmaAllocator, stagingBuffer, stagingBufferAllocation);
        }
    }


    private void createIndexBuffer() {
        try (var arena = Arena.ofConfined()) {
            var bufferSize = indices.length * Integer.BYTES;

            var pair = createBuffer(
                    bufferSize,
                    VkBufferUsageFlags.VK_BUFFER_USAGE_TRANSFER_SRC_BIT,
                    VmaAllocationCreateFlags.VMA_ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT,
                    null
            );
            var stagingBuffer = pair.first;
            var stagingBufferAllocation = pair.second;

            var ppData = PointerBuffer.allocate(arena);
            var result = vma.vmaMapMemory(vmaAllocator, stagingBufferAllocation, ppData);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to map index buffer memory, vulkan error code: " + VkResult.explain(result));
            }
            var pData = ppData.read().reinterpret(bufferSize);

            pData.copyFrom(MemorySegment.ofArray(indices));
            vma.vmaUnmapMemory(vmaAllocator, stagingBufferAllocation);

            pair = createBuffer(
                    bufferSize,
                    VkBufferUsageFlags.VK_BUFFER_USAGE_TRANSFER_DST_BIT | VkBufferUsageFlags.VK_BUFFER_USAGE_INDEX_BUFFER_BIT,
                    0,
                    null
            );
            indexBuffer = pair.first;
            indexBufferAllocation = pair.second;

            copyBuffer(stagingBuffer, indexBuffer, bufferSize);
            vma.vmaDestroyBuffer(vmaAllocator, stagingBuffer, stagingBufferAllocation);
        }
    }

    private record Pair<T1, T2>(T1 first, T2 second) {}

    private Pair<VkBuffer, VmaAllocation> createBuffer(
            int size,
            @enumtype(VkBufferUsageFlags.class) int usage,
            @enumtype(VmaAllocationCreateFlags.class) int vmaAllocationCreationFlags,
            @Nullable VmaAllocationInfo allocationInfo
    ) {
        try (var arena = Arena.ofConfined()) {
            var bufferInfo = VkBufferCreateInfo.allocate(arena);
            bufferInfo.size(size);
            bufferInfo.usage(usage);
            bufferInfo.sharingMode(VkSharingMode.VK_SHARING_MODE_EXCLUSIVE);

            var allocationCreateInfo = VmaAllocationCreateInfo.allocate(arena);
            allocationCreateInfo.usage(VmaMemoryUsage.VMA_MEMORY_USAGE_AUTO);
            allocationCreateInfo.flags(vmaAllocationCreationFlags);

            var pBuffer = VkBuffer.Buffer.allocate(arena);
            var pAllocation = VmaAllocation.Buffer.allocate(arena);
            var result = vma.vmaCreateBuffer(vmaAllocator, bufferInfo, allocationCreateInfo, pBuffer, pAllocation, allocationInfo);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create buffer, vulkan error code: " + VkResult.explain(result));
            }

            var buffer = pBuffer.read();
            var allocation = pAllocation.read();

            return new Pair<>(buffer, allocation);
        }
    }

    private void copyBuffer(VkBuffer srcBuffer, VkBuffer dstBuffer, int size) {
        try (var arena = Arena.ofConfined()) {
            var commandBuffer = beginSingleTimeCommands();

            var copyRegion = VkBufferCopy.allocate(arena);
            copyRegion.size(size);
            deviceCommands.vkCmdCopyBuffer(commandBuffer, srcBuffer, dstBuffer, 1, copyRegion);

            endSingleTimeCommands(commandBuffer);
        }
    }

    private void createDescriptorSetLayout() {
        try (var arena = Arena.ofConfined()) {
            var bindings = VkDescriptorSetLayoutBinding.allocate(arena, 2);
            var uboLayoutBinding = bindings[0];
            uboLayoutBinding.binding(0);
            uboLayoutBinding.descriptorType(VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER);
            uboLayoutBinding.descriptorCount(1);
            uboLayoutBinding.stageFlags(VkShaderStageFlags.VK_SHADER_STAGE_VERTEX_BIT);
            uboLayoutBinding.pImmutableSamplers(null);
            var samplerLayoutBinding = bindings[1];
            samplerLayoutBinding.binding(1);
            samplerLayoutBinding.descriptorCount(1);
            samplerLayoutBinding.descriptorType(VkDescriptorType.VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER);
            samplerLayoutBinding.stageFlags(VkShaderStageFlags.VK_SHADER_STAGE_FRAGMENT_BIT);

            var layoutInfo = VkDescriptorSetLayoutCreateInfo.allocate(arena);
            layoutInfo.bindingCount(2);
            layoutInfo.pBindings(bindings[0]);

            var pDescriptorSetLayout = VkDescriptorSetLayout.Buffer.allocate(arena);
            var result = deviceCommands.vkCreateDescriptorSetLayout(device, layoutInfo, null, pDescriptorSetLayout);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create descriptor set layout, vulkan error code: " + VkResult.explain(result));
            }
            descriptorSetLayout = pDescriptorSetLayout.read();
        }
    }

    private void createUniformBuffers() {
        var bufferSize = UniformBufferObject.bufferSize();
        uniformBuffers = new VkBuffer[MAX_FRAMES_IN_FLIGHT];
        uniformBuffersAllocation = new VmaAllocation[MAX_FRAMES_IN_FLIGHT];
        uniformBuffersMapped = new FloatBuffer[MAX_FRAMES_IN_FLIGHT];

        try (var arena = Arena.ofConfined()) {
            var allocationInfo = VmaAllocationInfo.allocate(arena);
            for (int i = 0; i < MAX_FRAMES_IN_FLIGHT; i++) {
                var pair = createBuffer(
                        bufferSize * Float.BYTES,
                        VkBufferUsageFlags.VK_BUFFER_USAGE_UNIFORM_BUFFER_BIT,
                        VmaAllocationCreateFlags.VMA_ALLOCATION_CREATE_MAPPED_BIT
                                | VmaAllocationCreateFlags.VMA_ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT,
                        allocationInfo
                );
                uniformBuffers[i] = pair.first;
                uniformBuffersAllocation[i] = pair.second;

                uniformBuffersMapped[i] = new FloatBuffer(allocationInfo.pMappedData()).reinterpret(bufferSize);
            }
        }
    }

    private void updateUniformBuffer() {
        var time = (System.currentTimeMillis() - startTime) / 1000.0f;

        var model = new Matrix4f().rotate((float) (Math.toRadians(90.0f) * time), 0.0f, 0.0f, 1.0f);
        var view = new Matrix4f().lookAt(
                2.0f, 2.0f, 2.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f
        );
        var proj = new Matrix4f().perspective(
                (float) Math.toRadians(45.0f),
                swapChainExtent.width() / (float) swapChainExtent.height(),
                0.1f,
                10.0f,
                true
        );

        new UniformBufferObject(model, view, proj).writeToBuffer(uniformBuffersMapped[currentFrame]);
    }

    private record UniformBufferObject(Matrix4f model, Matrix4f view, Matrix4f proj) {
        public static int bufferSize() {
            return 16 * 3;
        }

        public void writeToBuffer(FloatBuffer buffer) {
            assert buffer.size() >= bufferSize();

            model.get(buffer.segment().asByteBuffer().order(ByteOrder.nativeOrder()));
            view.get(buffer.offset(16).segment().asByteBuffer().order(ByteOrder.nativeOrder()));
            proj.get(buffer.offset(32).segment().asByteBuffer().order(ByteOrder.nativeOrder()));
        }
    }

    private void createDescriptorPool() {
        try (var arena = Arena.ofConfined()) {
            var poolSizes = VkDescriptorPoolSize.allocate(arena, 2);
            poolSizes[0].type(VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER);
            poolSizes[0].descriptorCount(MAX_FRAMES_IN_FLIGHT);
            poolSizes[1].type(VkDescriptorType.VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER);
            poolSizes[1].descriptorCount(MAX_FRAMES_IN_FLIGHT);

            var poolInfo = VkDescriptorPoolCreateInfo.allocate(arena);
            poolInfo.poolSizeCount(2);
            poolInfo.pPoolSizes(poolSizes[0]);
            poolInfo.maxSets(MAX_FRAMES_IN_FLIGHT);

            var pDescriptorPool = VkDescriptorPool.Buffer.allocate(arena);
            var result = deviceCommands.vkCreateDescriptorPool(device, poolInfo, null, pDescriptorPool);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create descriptor pool, vulkan error code: " + VkResult.explain(result));
            }
            descriptorPool = pDescriptorPool.read();
        }
    }

    private void createDescriptorSets() {
        try (var arena = Arena.ofConfined()) {
            var pLayouts = VkDescriptorSetLayout.Buffer.allocate(arena, MAX_FRAMES_IN_FLIGHT);
            pLayouts.write(0, descriptorSetLayout);
            pLayouts.write(1, descriptorSetLayout);

            var allocInfo = VkDescriptorSetAllocateInfo.allocate(arena);
            allocInfo.descriptorPool(descriptorPool);
            allocInfo.descriptorSetCount(MAX_FRAMES_IN_FLIGHT);
            allocInfo.pSetLayouts(pLayouts);

            var pDescriptorSets = VkDescriptorSet.Buffer.allocate(arena, MAX_FRAMES_IN_FLIGHT);
            var result = deviceCommands.vkAllocateDescriptorSets(device, allocInfo, pDescriptorSets);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to allocate descriptor sets, vulkan error code: " + VkResult.explain(result));
            }
            descriptorSets = pDescriptorSets.readAll();

            for (int i = 0; i < MAX_FRAMES_IN_FLIGHT; i++) {
                var bufferInfo = VkDescriptorBufferInfo.allocate(arena);
                bufferInfo.buffer(uniformBuffers[i]);
                bufferInfo.offset(0);
                bufferInfo.range((long) UniformBufferObject.bufferSize() * Float.BYTES);

                //The resources for a combined image sampler structure specified here
                var imageInfo = VkDescriptorImageInfo.allocate(arena);
                imageInfo.imageLayout(VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL);
                imageInfo.imageView(textureImageView);
                imageInfo.sampler(textureSampler);

                var descriptorWrites = VkWriteDescriptorSet.allocate(arena, 2);
                // this one in vertex shader
                descriptorWrites[0].dstSet(descriptorSets[i]);
                descriptorWrites[0].dstBinding(0);
                descriptorWrites[0].dstArrayElement(0);
                descriptorWrites[0].descriptorType(VkDescriptorType.VK_DESCRIPTOR_TYPE_UNIFORM_BUFFER);
                descriptorWrites[0].descriptorCount(1);
                descriptorWrites[0].pBufferInfo(bufferInfo);
                descriptorWrites[0].pImageInfo(null);
                descriptorWrites[0].pTexelBufferView(null);

                // this one in fragment shader
                descriptorWrites[1].dstSet(descriptorSets[i]);
                descriptorWrites[1].dstBinding(1);
                descriptorWrites[1].dstArrayElement(0);
                descriptorWrites[1].descriptorType(VkDescriptorType.VK_DESCRIPTOR_TYPE_COMBINED_IMAGE_SAMPLER);
                descriptorWrites[1].descriptorCount(1);
                descriptorWrites[1].pImageInfo(imageInfo);

                deviceCommands.vkUpdateDescriptorSets(device, 2, descriptorWrites[0], 0, null);
            }
        }
    }

    private VkCommandBuffer beginSingleTimeCommands() {
        try (var arena = Arena.ofConfined()) {
            var allocInfo = VkCommandBufferAllocateInfo.allocate(arena);
            allocInfo.level(VkCommandBufferLevel.VK_COMMAND_BUFFER_LEVEL_PRIMARY);
            allocInfo.commandPool(commandPool);
            allocInfo.commandBufferCount(1);

            var pCommandBuffer = VkCommandBuffer.Buffer.allocate(arena);
            var result = deviceCommands.vkAllocateCommandBuffers(device, allocInfo, pCommandBuffer);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to allocate command buffer, vulkan error code: " + VkResult.explain(result));
            }
            var commandBuffer = pCommandBuffer.read();

            var beginInfo = VkCommandBufferBeginInfo.allocate(arena);
            beginInfo.flags(VkCommandBufferUsageFlags.VK_COMMAND_BUFFER_USAGE_ONE_TIME_SUBMIT_BIT);
            result = deviceCommands.vkBeginCommandBuffer(commandBuffer, beginInfo);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to begin recording command buffer, vulkan error code: " + VkResult.explain(result));
            }

            return commandBuffer;
        }
    }

    private void endSingleTimeCommands(VkCommandBuffer commandBuffer) {
        deviceCommands.vkEndCommandBuffer(commandBuffer);

        try (var arena = Arena.ofConfined()) {
            var submitInfo = VkSubmitInfo.allocate(arena);
            submitInfo.commandBufferCount(1);
            var pCommandBuffers = VkCommandBuffer.Buffer.allocate(arena);
            pCommandBuffers.write(commandBuffer);
            submitInfo.pCommandBuffers(pCommandBuffers);

            deviceCommands.vkQueueSubmit(graphicsQueue, 1, submitInfo, null);
            deviceCommands.vkQueueWaitIdle(graphicsQueue);

            deviceCommands.vkFreeCommandBuffers(device, commandPool, 1, pCommandBuffers);
        }
    }

    private void transitionImageLayout(
            VkImage image,
            @enumtype(VkFormat.class) int format,
            @enumtype(VkImageLayout.class) int oldLayout,
            @enumtype(VkImageLayout.class) int newLayout,
            int mipLevels
    ) {
        try (var arena = Arena.ofConfined()) {
            var commandBuffer = beginSingleTimeCommands();

            var barrier = VkImageMemoryBarrier.allocate(arena);
            barrier.oldLayout(oldLayout);
            barrier.newLayout(newLayout);
            barrier.srcQueueFamilyIndex(Constants.VK_QUEUE_FAMILY_IGNORED);
            barrier.dstQueueFamilyIndex(Constants.VK_QUEUE_FAMILY_IGNORED);
            barrier.image(image);
            var subResourceRange = barrier.subresourceRange();
            if (newLayout == VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL) {
                subResourceRange.aspectMask(VkImageAspectFlags.VK_IMAGE_ASPECT_DEPTH_BIT);
                if (hasStencilComponent(format)) {
                    subResourceRange.aspectMask(subResourceRange.aspectMask()
                            | VkImageAspectFlags.VK_IMAGE_ASPECT_STENCIL_BIT);
                }
            }
            else {
                subResourceRange.aspectMask(VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT);
            }
            subResourceRange.baseMipLevel(0);
            subResourceRange.levelCount(mipLevels);
            subResourceRange.baseArrayLayer(0);
            subResourceRange.layerCount(1);

            @enumtype(VkPipelineStageFlags.class) int sourceStage;
            @enumtype(VkPipelineStageFlags.class) int destinationStage;

            if (oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED
                    && newLayout == VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL) {
                barrier.srcAccessMask(0);
                barrier.dstAccessMask(VkAccessFlags.VK_ACCESS_TRANSFER_WRITE_BIT);

                sourceStage = VkPipelineStageFlags.VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT;
                destinationStage = VkPipelineStageFlags.VK_PIPELINE_STAGE_TRANSFER_BIT;
            }
            else if (oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL
                    && newLayout == VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL) {
                barrier.srcAccessMask(VkAccessFlags.VK_ACCESS_TRANSFER_WRITE_BIT);
                barrier.dstAccessMask(VkAccessFlags.VK_ACCESS_SHADER_READ_BIT);

                sourceStage = VkPipelineStageFlags.VK_PIPELINE_STAGE_TRANSFER_BIT;
                destinationStage = VkPipelineStageFlags.VK_PIPELINE_STAGE_FRAGMENT_SHADER_BIT;
            }
            else if (oldLayout == VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED
                    && newLayout == VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL) {
                barrier.srcAccessMask(0);
                barrier.dstAccessMask(
                        VkAccessFlags.VK_ACCESS_DEPTH_STENCIL_ATTACHMENT_READ_BIT
                                | VkAccessFlags.VK_ACCESS_DEPTH_STENCIL_ATTACHMENT_WRITE_BIT
                );

                sourceStage = VkPipelineStageFlags.VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT;
                destinationStage = VkPipelineStageFlags.VK_PIPELINE_STAGE_EARLY_FRAGMENT_TESTS_BIT;
            }
            else {
                throw new RuntimeException(
                        "Unsupported layout transition from "
                                + VkImageLayout.explain(oldLayout)
                                + " to "
                                + VkImageLayout.explain(newLayout)
                );
            }

            deviceCommands.vkCmdPipelineBarrier(
                    commandBuffer,
                    sourceStage,
                    destinationStage,
                    0,
                    0, null,
                    0, null,
                    1, barrier
            );

            endSingleTimeCommands(commandBuffer);
        }
    }

    private void createTextureImage() {
        BufferedImage image;
        try (var stream = Application.class.getResourceAsStream(TEXTURE_PATH)) {
            if (stream == null) {
                throw new RuntimeException("Failed to load texture image");
            }
            image = ImageIO.read(stream);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load texture image", e);
        }

        var width = image.getWidth();
        var height = image.getHeight();
        var imageSize = width * height;
        var imageSizeBytes = imageSize * 4;

        textureMipLevels = (int) Math.floor(Math.log(Math.max(width, height)) / Math.log(2)) + 1;

        try (var arena = Arena.ofConfined()) {
            var pair = createBuffer(
                    imageSizeBytes,
                    VkBufferUsageFlags.VK_BUFFER_USAGE_TRANSFER_SRC_BIT,
                    VmaAllocationCreateFlags.VMA_ALLOCATION_CREATE_HOST_ACCESS_RANDOM_BIT,
                    null
            );
            var stagingBuffer = pair.first;
            var stagingBufferAllocation = pair.second;

            var ppData = PointerBuffer.allocate(arena);
            var result = vma.vmaMapMemory(vmaAllocator, stagingBufferAllocation, ppData);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to map texture image memory, vulkan error code: " + VkResult.explain(result));
            }
            var buffer = new ByteBuffer(ppData.read().reinterpret(imageSizeBytes));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    var color = new Color(image.getRGB(x, y), true);

                    var linearIndex = y * width + x;
                    buffer.write(linearIndex * 4L, (byte) color.getRed());
                    buffer.write(linearIndex * 4L + 1, (byte) color.getGreen());
                    buffer.write(linearIndex * 4L + 2, (byte) color.getBlue());
                    buffer.write(linearIndex * 4L + 3, (byte) color.getAlpha());
                }
            }
            vma.vmaUnmapMemory(vmaAllocator, stagingBufferAllocation);

            var pair2 = createImage(
                    width,
                    height,
                    textureMipLevels,
                    VkSampleCountFlags.VK_SAMPLE_COUNT_1_BIT,
                    VkFormat.VK_FORMAT_R8G8B8A8_SRGB,
                    VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
                    VkImageUsageFlags.VK_IMAGE_USAGE_TRANSFER_DST_BIT
                            | VkImageUsageFlags.VK_IMAGE_USAGE_SAMPLED_BIT
                            | VkImageUsageFlags.VK_IMAGE_USAGE_TRANSFER_SRC_BIT
            );
            textureImage = pair2.first;
            textureImageAllocation = pair2.second;

            transitionImageLayout(
                    textureImage,
                    VkFormat.VK_FORMAT_R8G8B8A8_SRGB,
                    VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED,
                    VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL,
                    textureMipLevels
            );
            copyBufferToImage(stagingBuffer, textureImage, width, height);
            generateMipmaps(textureImage, VkFormat.VK_FORMAT_R8G8B8A8_SRGB, width, height, textureMipLevels);

            vma.vmaDestroyBuffer(vmaAllocator, stagingBuffer, stagingBufferAllocation);
        }
    }

    private Pair<VkImage, VmaAllocation> createImage(
            int width,
            int height,
            int mipLevels,
            @enumtype(VkSampleCountFlags.class) int numSamples,
            @enumtype(VkFormat.class) int format,
            @enumtype(VkImageTiling.class) int tiling,
            @enumtype(VkImageUsageFlags.class) int usage
    ) {
        try (var arena = Arena.ofConfined()) {
            var imageInfo = VkImageCreateInfo.allocate(arena);
            imageInfo.imageType(VkImageType.VK_IMAGE_TYPE_2D);
            imageInfo.extent().width(width);
            imageInfo.extent().height(height);
            imageInfo.extent().depth(1);
            imageInfo.mipLevels(mipLevels);
            imageInfo.arrayLayers(1);
            imageInfo.format(format);
            imageInfo.tiling(tiling);
            imageInfo.initialLayout(VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED);
            imageInfo.usage(usage);
            imageInfo.samples(numSamples);
            imageInfo.sharingMode(VkSharingMode.VK_SHARING_MODE_EXCLUSIVE);

            var allocationCreateInfo = VmaAllocationCreateInfo.allocate(arena);
            allocationCreateInfo.usage(VmaMemoryUsage.VMA_MEMORY_USAGE_GPU_ONLY);

            var pImage = VkImage.Buffer.allocate(arena);
            var pAllocation = VmaAllocation.Buffer.allocate(arena);
            var result = vma.vmaCreateImage(vmaAllocator, imageInfo, allocationCreateInfo, pImage, pAllocation, null);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create image, vulkan error code: " + VkResult.explain(result));
            }
            var image = pImage.read();
            var allocation = pAllocation.read();
            return new Pair<>(image, allocation);
        }
    }

    private void copyBufferToImage(
            VkBuffer buffer,
            VkImage image,
            int width,
            int height
    ) {
        try (var arena = Arena.ofConfined()) {
            var commandBuffer = beginSingleTimeCommands();

            var region = VkBufferImageCopy.allocate(arena);
            region.bufferOffset(0);
            region.bufferRowLength(0);
            region.bufferImageHeight(0);

            var imageSubresource = region.imageSubresource();
            imageSubresource.aspectMask(VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT);
            imageSubresource.mipLevel(0);
            imageSubresource.baseArrayLayer(0);
            imageSubresource.layerCount(1);

            var imageOffset = region.imageOffset();
            imageOffset.x(0);
            imageOffset.y(0);
            imageOffset.z(0);
            var imageExtent = region.imageExtent();
            imageExtent.width(width);
            imageExtent.height(height);
            imageExtent.depth(1);

            deviceCommands.vkCmdCopyBufferToImage(
                    commandBuffer,
                    buffer,
                    image,
                    VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL,
                    1,
                    region
            );

            endSingleTimeCommands(commandBuffer);
        }
    }

    private VkImageView createImageView(
            VkImage image,
            @enumtype(VkFormat.class) int format,
            @enumtype(VkImageAspectFlags.class) int aspect,
            int mipLevels
    ) {
        try (var arena = Arena.ofConfined()) {
            var viewInfo = VkImageViewCreateInfo.allocate(arena);
            viewInfo.image(image);
            viewInfo.viewType(VkImageViewType.VK_IMAGE_VIEW_TYPE_2D);
            viewInfo.format(format);

            var subresourceRange = viewInfo.subresourceRange();
            subresourceRange.aspectMask(aspect);
            subresourceRange.baseMipLevel(0);
            subresourceRange.levelCount(mipLevels);
            subresourceRange.baseArrayLayer(0);
            subresourceRange.layerCount(1);

            var pImageView = VkImageView.Buffer.allocate(arena);
            var result = deviceCommands.vkCreateImageView(device, viewInfo, null, pImageView);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create image view, vulkan error code: " + VkResult.explain(result));
            }
            return pImageView.read();
        }
    }

    private void createTextureImageView() {
        textureImageView = createImageView(
                textureImage,
                VkFormat.VK_FORMAT_R8G8B8A8_SRGB,
                VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT,
                textureMipLevels
        );
    }

    private void createTextureSampler() {
        try (var arena = Arena.ofConfined()) {
            var samplerInfo = VkSamplerCreateInfo.allocate(arena);
            samplerInfo.magFilter(VkFilter.VK_FILTER_LINEAR);
            samplerInfo.minFilter(VkFilter.VK_FILTER_LINEAR);
            samplerInfo.addressModeU(VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT);
            samplerInfo.addressModeV(VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT);
            samplerInfo.addressModeW(VkSamplerAddressMode.VK_SAMPLER_ADDRESS_MODE_REPEAT);

            var properties = VkPhysicalDeviceProperties.allocate(arena);
            instanceCommands.vkGetPhysicalDeviceProperties(physicalDevice, properties);
            samplerInfo.anisotropyEnable(Constants.VK_TRUE);
            samplerInfo.maxAnisotropy(properties.limits().maxSamplerAnisotropy());
            samplerInfo.borderColor(VkBorderColor.VK_BORDER_COLOR_INT_OPAQUE_BLACK);
            samplerInfo.unnormalizedCoordinates(Constants.VK_FALSE);
            samplerInfo.compareEnable(Constants.VK_FALSE);
            samplerInfo.compareOp(VkCompareOp.VK_COMPARE_OP_ALWAYS);
            samplerInfo.mipmapMode(VkSamplerMipmapMode.VK_SAMPLER_MIPMAP_MODE_LINEAR);
            samplerInfo.minLod(0.0f);
//            samplerInfo.minLod(textureMipLevels / 2.0f);
            samplerInfo.maxLod((float) textureMipLevels);
            samplerInfo.mipLodBias(0.0f);

            var pSampler = VkSampler.Buffer.allocate(arena);
            var result = deviceCommands.vkCreateSampler(device, samplerInfo, null, pSampler);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create texture sampler, vulkan error code: " + VkResult.explain(result));
            }
            textureSampler = pSampler.read();
        }
    }

    private @enumtype(VkFormat.class) int findDepthFormat() {
        return findSupportedFormat(
                new int[] {
                        VkFormat.VK_FORMAT_D32_SFLOAT,
                        VkFormat.VK_FORMAT_D32_SFLOAT_S8_UINT,
                        VkFormat.VK_FORMAT_D24_UNORM_S8_UINT
                },
                VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
                VkFormatFeatureFlags.VK_FORMAT_FEATURE_DEPTH_STENCIL_ATTACHMENT_BIT
        );
    }

    private @enumtype(VkFormat.class) int findSupportedFormat(
            @enumtype(VkFormat.class) int[] candidates,
            @enumtype(VkImageTiling.class) int tiling,
            @enumtype(VkFormatFeatureFlags.class) int features
    ) {
        for (var format : candidates) {
            try (var arena = Arena.ofConfined()) {
                var props = VkFormatProperties.allocate(arena);
                instanceCommands.vkGetPhysicalDeviceFormatProperties(physicalDevice, format, props);

                if (tiling == VkImageTiling.VK_IMAGE_TILING_LINEAR && (props.linearTilingFeatures() & features) == features) {
                    return format;
                }
                else if (tiling == VkImageTiling.VK_IMAGE_TILING_OPTIMAL && (props.optimalTilingFeatures() & features) == features) {
                    return format;
                }
            }
        }

        throw new RuntimeException("Failed to find supported format");
    }

    private boolean hasStencilComponent(@enumtype(VkFormat.class) int format) {
        return format == VkFormat.VK_FORMAT_D32_SFLOAT_S8_UINT || format == VkFormat.VK_FORMAT_D24_UNORM_S8_UINT;
    }

    private void createDepthResources() {
        var pair = createImage(
                swapChainExtent.width(),
                swapChainExtent.height(),
                1,
                msaaSamples,
                depthFormat,
                VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
                VkImageUsageFlags.VK_IMAGE_USAGE_DEPTH_STENCIL_ATTACHMENT_BIT
        );
        depthImage = pair.first;
        depthImageAllocation = pair.second;
        depthImageView = createImageView(depthImage, depthFormat, VkImageAspectFlags.VK_IMAGE_ASPECT_DEPTH_BIT, 1);

        transitionImageLayout(
                depthImage,
                depthFormat,
                VkImageLayout.VK_IMAGE_LAYOUT_UNDEFINED,
                VkImageLayout.VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL,
                1
        );
    }

    private void loadModel() {
        try (var stream = Application.class.getResourceAsStream("/model/viking_room.obj")) {
            if (stream == null) {
                throw new RuntimeException("Failed to load model");
            }

            var obj = ObjReader.read(stream);
            obj = ObjUtils.convertToRenderable(obj);

            indices = ObjData.getFaceVertexIndicesArray(obj);

            var verticesArray = ObjData.getVerticesArray(obj);
            var texCoordsArray = ObjData.getTexCoordsArray(obj, 2);
            vertices = new float[obj.getNumVertices() * 8];
            for (int i = 0; i < obj.getNumVertices(); i++) {
                // vec3 pos
                vertices[i * 8] = verticesArray[i * 3];
                vertices[i * 8 + 1] = verticesArray[i * 3 + 1];
                vertices[i * 8 + 2] = verticesArray[i * 3 + 2];
                // vec3 color
                vertices[i * 8 + 3] = 1.0f;
                vertices[i * 8 + 4] = 1.0f;
                vertices[i * 8 + 5] = 1.0f;
                // vec2 texCoord
                vertices[i * 8 + 6] = texCoordsArray[i * 2];
                vertices[i * 8 + 7] = 1.0f - texCoordsArray[i * 2 + 1];
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load model", e);
        }
    }

    private void generateMipmaps(
            VkImage image,
            @enumtype(VkFormat.class) int imageFormat,
            int texWidth,
            int texHeight,
            int mipLevels
    ) {
        try (var arena = Arena.ofConfined()) {
            var formatProperties = VkFormatProperties.allocate(arena);
            instanceCommands.vkGetPhysicalDeviceFormatProperties(physicalDevice, imageFormat, formatProperties);

            if ((formatProperties.optimalTilingFeatures() & VkFormatFeatureFlags.VK_FORMAT_FEATURE_SAMPLED_IMAGE_FILTER_LINEAR_BIT) == 0) {
                throw new RuntimeException("Texture image format does not support linear blitting");
            }

            var commandBuffer = beginSingleTimeCommands();
            var barrier = VkImageMemoryBarrier.allocate(arena);
            barrier.image(image);
            barrier.srcQueueFamilyIndex(Constants.VK_QUEUE_FAMILY_IGNORED);
            barrier.dstQueueFamilyIndex(Constants.VK_QUEUE_FAMILY_IGNORED);
            barrier.subresourceRange().aspectMask(VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT);
            barrier.subresourceRange().baseArrayLayer(0);
            barrier.subresourceRange().layerCount(1);
            barrier.subresourceRange().levelCount(1);

            var blit = VkImageBlit.allocate(arena);

            var mipWidth = texWidth;
            var mipHeight = texHeight;
            for (var i = 1; i < mipLevels; i++) {
                barrier.subresourceRange().baseMipLevel(i - 1);
                barrier.oldLayout(VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL);
                barrier.newLayout(VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_SRC_OPTIMAL);
                barrier.srcAccessMask(VkAccessFlags.VK_ACCESS_TRANSFER_WRITE_BIT);
                barrier.dstAccessMask(VkAccessFlags.VK_ACCESS_TRANSFER_READ_BIT);
                deviceCommands.vkCmdPipelineBarrier(
                        commandBuffer,
                        VkPipelineStageFlags.VK_PIPELINE_STAGE_TRANSFER_BIT,
                        VkPipelineStageFlags.VK_PIPELINE_STAGE_TRANSFER_BIT,
                        0,
                        0, null,
                        0, null,
                        1, barrier
                );

                var srcOffsets = blit.srcOffsets();
                srcOffsets[0].x(0);
                srcOffsets[0].y(0);
                srcOffsets[0].z(0);
                srcOffsets[1].x(mipWidth);
                srcOffsets[1].y(mipHeight);
                srcOffsets[1].z(1);
                var srcSubresource = blit.srcSubresource();
                srcSubresource.aspectMask(VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT);
                srcSubresource.mipLevel(i - 1);
                srcSubresource.baseArrayLayer(0);
                srcSubresource.layerCount(1);
                var dstOffsets = blit.dstOffsets();
                dstOffsets[0].x(0);
                dstOffsets[0].y(0);
                dstOffsets[0].z(0);
                dstOffsets[1].x(mipWidth > 1 ? mipWidth / 2 : 1);
                dstOffsets[1].y(mipHeight > 1 ? mipHeight / 2 : 1);
                dstOffsets[1].z(1);
                var dstSubresource = blit.dstSubresource();
                dstSubresource.aspectMask(VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT);
                dstSubresource.mipLevel(i);
                dstSubresource.baseArrayLayer(0);
                dstSubresource.layerCount(1);

                deviceCommands.vkCmdBlitImage(
                        commandBuffer,
                        image,
                        VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_SRC_OPTIMAL,
                        image,
                        VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL,
                        1,
                        blit,
                        VkFilter.VK_FILTER_LINEAR
                );

                barrier.oldLayout(VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_SRC_OPTIMAL);
                barrier.newLayout(VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL);
                barrier.srcAccessMask(VkAccessFlags.VK_ACCESS_TRANSFER_READ_BIT);
                barrier.dstAccessMask(VkAccessFlags.VK_ACCESS_SHADER_READ_BIT);
                deviceCommands.vkCmdPipelineBarrier(
                        commandBuffer,
                        VkPipelineStageFlags.VK_PIPELINE_STAGE_TRANSFER_BIT,
                        VkPipelineStageFlags.VK_PIPELINE_STAGE_FRAGMENT_SHADER_BIT,
                        0,
                        0, null,
                        0, null,
                        1, barrier
                );

                if (mipWidth > 1) {
                    mipWidth /= 2;
                }
                if (mipHeight > 1) {
                    mipHeight /= 2;
                }
            }

            barrier.subresourceRange().baseMipLevel(mipLevels - 1);
            barrier.oldLayout(VkImageLayout.VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL);
            barrier.newLayout(VkImageLayout.VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL);
            barrier.srcAccessMask(VkAccessFlags.VK_ACCESS_TRANSFER_WRITE_BIT);
            barrier.dstAccessMask(VkAccessFlags.VK_ACCESS_SHADER_READ_BIT);

            deviceCommands.vkCmdPipelineBarrier(
                    commandBuffer,
                    VkPipelineStageFlags.VK_PIPELINE_STAGE_TRANSFER_BIT,
                    VkPipelineStageFlags.VK_PIPELINE_STAGE_FRAGMENT_SHADER_BIT,
                    0,
                    0, null,
                    0, null,
                    1, barrier
            );

            endSingleTimeCommands(commandBuffer);
        }
    }

    private @enumtype(VkSampleCountFlags.class) int getMaxUsableSampleCount() {
        try (var arena = Arena.ofConfined()) {
            var physicalDeviceProperties = VkPhysicalDeviceProperties.allocate(arena);
            instanceCommands.vkGetPhysicalDeviceProperties(physicalDevice, physicalDeviceProperties);

            var counts = physicalDeviceProperties.limits().framebufferColorSampleCounts()
                    & physicalDeviceProperties.limits().framebufferDepthSampleCounts();

            if ((counts & VkSampleCountFlags.VK_SAMPLE_COUNT_64_BIT) != 0) {
                return VkSampleCountFlags.VK_SAMPLE_COUNT_64_BIT;
            }
            if ((counts & VkSampleCountFlags.VK_SAMPLE_COUNT_32_BIT) != 0) {
                return VkSampleCountFlags.VK_SAMPLE_COUNT_32_BIT;
            }
            if ((counts & VkSampleCountFlags.VK_SAMPLE_COUNT_16_BIT) != 0) {
                return VkSampleCountFlags.VK_SAMPLE_COUNT_16_BIT;
            }
            if ((counts & VkSampleCountFlags.VK_SAMPLE_COUNT_8_BIT) != 0) {
                return VkSampleCountFlags.VK_SAMPLE_COUNT_8_BIT;
            }
            if ((counts & VkSampleCountFlags.VK_SAMPLE_COUNT_4_BIT) != 0) {
                return VkSampleCountFlags.VK_SAMPLE_COUNT_4_BIT;
            }
            if ((counts & VkSampleCountFlags.VK_SAMPLE_COUNT_2_BIT) != 0) {
                return VkSampleCountFlags.VK_SAMPLE_COUNT_2_BIT;
            }

            return VkSampleCountFlags.VK_SAMPLE_COUNT_1_BIT;
        }
    }

    private void createColorResources() {
        var colorFormat = swapChainImageFormat;

        var pair = createImage(
                swapChainExtent.width(),
                swapChainExtent.height(),
                1,
                msaaSamples,
                colorFormat,
                VkImageTiling.VK_IMAGE_TILING_OPTIMAL,
                VkImageUsageFlags.VK_IMAGE_USAGE_TRANSIENT_ATTACHMENT_BIT
                        | VkImageUsageFlags.VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT
        );
        colorImage = pair.first;
        colorImageAllocation = pair.second;
        colorImageView = createImageView(colorImage, colorFormat, VkImageAspectFlags.VK_IMAGE_ASPECT_COLOR_BIT, 1);
    }

    private void createVMA() {
        System.loadLibrary("vma");
        vma = new VMA(Loader::loadFunction);
        VMAJavaTraceUtil.enableJavaTraceForVMA();

        try (var arena = Arena.ofConfined()) {
            var vmaVulkanFunctions = VmaVulkanFunctions.allocate(arena);
            VMAUtil.fillVulkanFunctions(
                    vmaVulkanFunctions,
                    staticCommands,
                    entryCommands,
                    instanceCommands,
                    deviceCommands
            );

            var vmaCreateInfo = VmaAllocatorCreateInfo.allocate(arena);
            vmaCreateInfo.instance(instance);
            vmaCreateInfo.physicalDevice(physicalDevice);
            vmaCreateInfo.device(device);
            vmaCreateInfo.pVulkanFunctions(vmaVulkanFunctions);
            vmaCreateInfo.vulkanApiVersion(Version.VK_API_VERSION_1_1);

            var pVmaAllocator = VmaAllocator.Buffer.allocate(arena);
            var result = vma.vmaCreateAllocator(vmaCreateInfo, pVmaAllocator);
            if (result != VkResult.VK_SUCCESS) {
                throw new RuntimeException("Failed to create VMA allocator, vulkan error code: " + VkResult.explain(result));
            }

            vmaAllocator = pVmaAllocator.read();
        }
    }
}
